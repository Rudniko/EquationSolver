package pl.kurs.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component("-")
public class Subtractor implements ArithmeticOperator {

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
