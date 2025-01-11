package pl.kurs.services;

import pl.kurs.exceptions.InvalidEquationFormatException;
import pl.kurs.exceptions.UnknownOperatorException;

import java.math.BigInteger;

public interface IEquationSolver {
    BigInteger solve(String equation) throws InvalidEquationFormatException, UnknownOperatorException;
}
