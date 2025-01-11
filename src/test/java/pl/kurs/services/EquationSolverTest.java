package pl.kurs.services;

import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import pl.kurs.config.BeansConfig;
import pl.kurs.dao.EquationsDao;
import pl.kurs.dao.IEquationsDao;
import pl.kurs.exceptions.InvalidEquationFormatException;
import pl.kurs.exceptions.UnknownOperatorException;
import pl.kurs.models.*;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class EquationSolverTest {

    @Mock
    private IEquationsDao equationsDao;
    @Mock
    private IEquationValidator validator;

    private EquationSolver equationSolver;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Map<String, ArithmeticOperator> operators = new HashMap<>();
        operators.put("+", new Adder());
        operators.put("-", new Subtractor());
        operators.put("*", new Multiplier());
        operators.put("/", new Divider());

        equationSolver = new EquationSolver(operators, equationsDao, validator);
    }

    @Test
    public void shouldReturn6AsResult() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "2 + 2 * 2";

        BigInteger result = equationSolver.solve(equation);

        assertEquals(BigInteger.valueOf(6), result);
    }

    @Test
    public void shouldReturn0AsResult() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "10 / 0";

        BigInteger result = equationSolver.solve(equation);

        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void shouldReturn11AsResult() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "5 + 5 - 3 / 3 + 2";

        BigInteger result = equationSolver.solve(equation);

        assertEquals(BigInteger.valueOf(11), result);
    }

    @Test
    public void shouldReturn3_000_000_000AsResult() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "1000000000 + 2000000000";

        BigInteger result = equationSolver.solve(equation);

        assertEquals(BigInteger.valueOf(3000000000L), result);
    }


    @Test
    public void shouldValidateGivenEquation() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "2 + 2";

        equationSolver.solve(equation);

        Mockito.verify(validator, Mockito.times(1)).checkEquation(equation);
    }

    @Test
    public void shouldSaveSolvedEquationToDB() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "2 + 2 * 2";

        ArgumentCaptor<SolvedEquation> captor = ArgumentCaptor.forClass(SolvedEquation.class);

        BigInteger result = equationSolver.solve(equation);

        assertEquals(BigInteger.valueOf(6), result);

        Mockito.verify(equationsDao, Mockito.times(1))
                .saveSolvedEquation(captor.capture());

        SolvedEquation solvedEquation = captor.getValue();
        assertEquals(equation, solvedEquation.getEquation());
        assertEquals(result, solvedEquation.getResult());
    }
    @Test
    public void shouldNotValidateTheEquationAndNotContinueSolving1() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "2 +";

        Mockito.doThrow(new InvalidEquationFormatException("Invalid equation format"))
                .when(validator).checkEquation(equation);

        assertThrows(InvalidEquationFormatException.class, () -> equationSolver.solve(equation));

        Mockito.verify(validator, Mockito.times(1)).checkEquation(equation);
        Mockito.verify(equationsDao, Mockito.never()).saveSolvedEquation(Mockito.any(SolvedEquation.class));
    }

    @Test
    public void shouldNotValidateTheEquationAndNotContinueSolving2() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "3";

        Mockito.doThrow(new InvalidEquationFormatException("Invalid equation format"))
                .when(validator).checkEquation(equation);

        assertThrows(InvalidEquationFormatException.class, () -> equationSolver.solve(equation));

        Mockito.verify(validator, Mockito.times(1)).checkEquation(equation);
        Mockito.verify(equationsDao, Mockito.never()).saveSolvedEquation(Mockito.any(SolvedEquation.class));
    }

    @Test
    public void shouldNotValidateTheEquationAndNotContinueSolving3() throws InvalidEquationFormatException, UnknownOperatorException {
        String equation = "3 + 3 % 3";

        Mockito.doThrow(new UnknownOperatorException("Unknown operator"))
                .when(validator).checkEquation(equation);

        assertThrows(UnknownOperatorException.class, () -> equationSolver.solve(equation));

        Mockito.verify(validator, Mockito.times(1)).checkEquation(equation);
        Mockito.verify(equationsDao, Mockito.never()).saveSolvedEquation(Mockito.any(SolvedEquation.class));
    }




}