package pl.kurs.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class DividerTest {

    private Divider divider;

    @Before
    public void setUp() {
        divider = new Divider();
    }

    @Test
    public void shouldDivideTwoNumbers() {
        assertEquals(new BigInteger("2"), divider.calculate(new BigInteger("6"), new BigInteger("3")));
    }

    @Test
    public void shouldReturnZeroWhenDividingByZero() {
        assertEquals(BigInteger.ZERO, divider.calculate(new BigInteger("5"), new BigInteger("0")));
    }
}