package pl.kurs.services;

import pl.kurs.dao.IEquationsDao;
import pl.kurs.exceptions.InvalidEquationFormatException;
import pl.kurs.exceptions.UnknownOperatorException;
import pl.kurs.models.ArithmeticOperator;
import pl.kurs.models.SolvedEquation;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EquationSolver implements IEquationSolver {

    private final Map<String, ArithmeticOperator> operators;
    private final IEquationsDao equationsDao;
    private final IEquationValidator validator;

    public EquationSolver(Map<String, ArithmeticOperator> operators, IEquationsDao equationsDao, IEquationValidator validator) {
        this.operators = operators;
        this.equationsDao = equationsDao;
        this.validator = validator;
    }

    @Override
    public BigInteger solve(String equation) throws InvalidEquationFormatException, UnknownOperatorException {
        validator.checkEquation(equation);
        equation = equation.trim();

        String[] equationParts = equation.split(" ");
        List<String> parts = new ArrayList<>(List.of(equationParts));

        while (parts.size() > 1) {
            calculateByPriority(parts);
        }

        BigInteger result = new BigInteger(parts.get(0));

        SolvedEquation solvedEquation = new SolvedEquation(Timestamp.from(Instant.now()), equation, result);
        equationsDao.saveSolvedEquation(solvedEquation);

        return result;
    }

    private void calculateByPriority(List<String> equationParts) throws UnknownOperatorException {
        int highestPriority = findTheHighestOperatorPriorityInEquation(equationParts);

        for (int i = 0; i < equationParts.size(); i++) {
            String part = equationParts.get(i);

            ArithmeticOperator operator = operators.get(part);
            if (operator != null && operator.getPriority() == highestPriority) {

                BigInteger leftNumber = new BigInteger(equationParts.get(i - 1));
                BigInteger rightNumber = new BigInteger(equationParts.get(i + 1));

                BigInteger result = operator.calculate(leftNumber, rightNumber);

                equationParts.set(i - 1, String.valueOf(result));
                equationParts.remove(i);
                equationParts.remove(i);
                return;
            }
        }
    }

    private int findTheHighestOperatorPriorityInEquation(List<String> equationParts) throws UnknownOperatorException {
        return equationParts.stream()
                .map(operators::get)
                .filter(Objects::nonNull)
                .mapToInt(ArithmeticOperator::getPriority)
                .max()
                .orElseThrow(() -> new UnknownOperatorException("Unknown operator"));
    }
}
