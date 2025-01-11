package pl.kurs.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "solved_equations")
public class SolvedEquation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String equation;
    private BigInteger result;
    private Timestamp date;

    public SolvedEquation() {
    }

    public SolvedEquation(Timestamp date, String equation, BigInteger result) {
        this.date = date;
        this.equation = equation;
        this.result = result;
    }

    public String getEquation() {
        return equation;
    }

    public BigInteger getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolvedEquation that = (SolvedEquation) o;
        return Objects.equals(id, that.id) && Objects.equals(equation, that.equation) && Objects.equals(result, that.result) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equation, result, date);
    }
}
