package pl.kurs.services;

import pl.kurs.exceptions.InvalidEquationFormatException;
import pl.kurs.exceptions.UnknownOperatorException;

public interface IEquationValidator {

    void checkEquation(String equation) throws InvalidEquationFormatException, UnknownOperatorException;
}
