package pl.kurs.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component("*")
public class Multiplier implements ArithmeticOperator {

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
