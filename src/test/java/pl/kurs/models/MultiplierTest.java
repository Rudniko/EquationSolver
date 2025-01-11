package pl.kurs.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class MultiplierTest {

    private Multiplier multiplier;

    @Before
    public void setUp() {
        multiplier = new Multiplier();
    }

    @Test
    public void shouldMultiplyTwoNumbers() {
        assertEquals(new BigInteger("6"), multiplier.calculate(new BigInteger("2"), new BigInteger("3")));
    }

    @Test
    public void shouldReturnZeroMultiplyingByZero() {
        assertEquals(BigInteger.ZERO, multiplier.calculate(new BigInteger("0"), new BigInteger("5")));
    }

}