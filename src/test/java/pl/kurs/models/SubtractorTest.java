package pl.kurs.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class SubtractorTest {

    private Subtractor subtractor;

    @Before
    public void setUp() {
        subtractor = new Subtractor();
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        assertEquals(new BigInteger("1"), subtractor.calculate(new BigInteger("3"), new BigInteger("2")));
    }

}