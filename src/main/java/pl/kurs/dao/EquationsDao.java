package pl.kurs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import pl.kurs.models.SolvedEquation;
import org.springframework.stereotype.Repository;


@Repository
public class EquationsDao implements IEquationsDao {

    @PersistenceUnit
    private EntityManagerFactory factory;


    @Override
    public void saveSolvedEquation(SolvedEquation solvedEquation) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(solvedEquation);
        tx.commit();
        manager.close();
    }

}
