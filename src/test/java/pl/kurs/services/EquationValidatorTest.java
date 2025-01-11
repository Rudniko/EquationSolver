package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.exceptions.InvalidEquationFormatException;
import pl.kurs.exceptions.UnknownOperatorException;
import pl.kurs.models.*;

import java.util.Map;

import static org.junit.Assert.*;

public class EquationValidatorTest {

    private EquationValidator equationValidator;

    @Before
    public void init() {

        Map<String, ArithmeticOperator> operators = Map.of(
                "+", new Adder(),
                "-", new Subtractor(),
                "*", new Multiplier(),
                "/", new Divider()
        );

        equationValidator = new EquationValidator(operators);
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsNull() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation(null));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Equation cannot be null or empty", e.getMessage());

    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsEmpty() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation(""));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Equation cannot be null or empty", e.getMessage());

    }


    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsInvalid1() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation("2 + 2 * "));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Invalid equation format", e.getMessage());
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsInvalid2() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation("wrong"));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Invalid equation format", e.getMessage());
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsInvalid3() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation("    "));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Invalid equation format", e.getMessage());
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsInvalid4() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation("S + 2"));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Invalid equation format", e.getMessage());
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsInvalid5() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation("2"));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Invalid equation format", e.getMessage());
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationIsInvalid6() {
        Exception e = assertThrows(InvalidEquationFormatException.class, () -> equationValidator.checkEquation("B"));

        assertEquals(InvalidEquationFormatException.class, e.getClass());
        assertEquals("Invalid equation format", e.getMessage());
    }

    @Test
    public void shouldThrowUnknownOperatorExceptionWhenOperatorIsNotSupported1() {
        Exception e = assertThrows(UnknownOperatorException.class, () -> equationValidator.checkEquation("2 % 2"));

        assertEquals(UnknownOperatorException.class, e.getClass());
        assertEquals("Uknown operator  %", e.getMessage());
    }

    @Test
    public void shouldThrowUnknownOperatorExceptionWhenOperatorIsNotSupported2() {
        Exception e = assertThrows(UnknownOperatorException.class, () -> equationValidator.checkEquation("2 ^ 2"));

        assertEquals(UnknownOperatorException.class, e.getClass());
        assertEquals("Uknown operator  ^", e.getMessage());
    }

    @Test
    public void shouldThrowUnknownOperatorExceptionWhenOperatorIsNotSupported3() {
        Exception e = assertThrows(UnknownOperatorException.class, () -> equationValidator.checkEquation("2 ++ 2"));

        assertEquals(UnknownOperatorException.class, e.getClass());
        assertEquals("Uknown operator  ++", e.getMessage());
    }

    @Test
    public void shouldThrowUnknownOperatorExceptionWhenOperatorIsNotSupported4() {
        Exception e = assertThrows(UnknownOperatorException.class, () -> equationValidator.checkEquation("2 3 2"));

        assertEquals(UnknownOperatorException.class, e.getClass());
        assertEquals("Uknown operator  3", e.getMessage());
    }

    @Test
    public void shouldNotThrowAnyExceptionWhenEquationIsValid() throws InvalidEquationFormatException, UnknownOperatorException {
        equationValidator.checkEquation("2 + 2 * 2");
        equationValidator.checkEquation("3 / 0 + 2 - 5 * 9 - 1");
        equationValidator.checkEquation("10 / 0");
        equationValidator.checkEquation("1241 / 7");
        equationValidator.checkEquation(" 4 * 4 + 2   ");
    }
}