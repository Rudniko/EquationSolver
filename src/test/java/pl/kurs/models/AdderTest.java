package pl.kurs.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class AdderTest {

    private Adder adder;

    @Before
    public void setUp() {
        adder = new Adder();
    }

    @Test
    public void shouldAddTwoNumbers() {
        assertEquals(new BigInteger("5"), adder.calculate(new BigInteger("2"), new BigInteger("3")));
    }

}