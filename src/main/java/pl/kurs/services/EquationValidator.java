package pl.kurs.services;

import pl.kurs.exceptions.InvalidEquationFormatException;
import org.springframework.stereotype.Service;
import pl.kurs.exceptions.UnknownOperatorException;
import pl.kurs.models.ArithmeticOperator;

import java.math.BigInteger;
import java.util.Map;

@Service
public class EquationValidator implements IEquationValidator {

    private final Map<String, ArithmeticOperator> operators;

    public EquationValidator(Map<String, ArithmeticOperator> operators) {
        this.operators = operators;
    }

    @Override
    public void checkEquation(String equation) throws InvalidEquationFormatException, UnknownOperatorException {
        if (equation == null || equation.isEmpty()) {
            throw new InvalidEquationFormatException("Equation cannot be null or empty");
        }

        equation = equation.trim();

        String[] equationParts = equation.split(" ");

        if (equationParts.length % 2 == 0 || equation.length() == 1) {
            throw new InvalidEquationFormatException("Invalid equation format");
        }

        for (int i = 0; i < equationParts.length; i++) {
            if (i % 2 == 0) {
                if (!isANumber(equationParts[i])) {
                    throw new InvalidEquationFormatException("Invalid equation format");
                }
            } else {
                if (!operators.containsKey(equationParts[i])) {
                    throw new UnknownOperatorException("Uknown operator  " + equationParts[i]);
                }
            }
        }
    }

    private boolean isANumber(String part) {
        try {
            new BigInteger(part);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
