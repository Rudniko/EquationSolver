package pl.kurs.dao;

import pl.kurs.models.SolvedEquation;

public interface IEquationsDao {

    void saveSolvedEquation(SolvedEquation solvedEquation);
}
