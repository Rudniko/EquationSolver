package pl.kurs.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component("/")
public class Divider implements ArithmeticOperator {

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        return a.divide(b);
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
