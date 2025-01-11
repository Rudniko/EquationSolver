package pl.kurs.models;

import java.math.BigInteger;

public interface ArithmeticOperator {
    BigInteger calculate(BigInteger a, BigInteger b);
    int getPriority();
}
