package dao;

import entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezzoDAO {
    private final EntityManager entityManager;


    public MezzoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Mezzo mezzo) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mezzo);
        transaction.commit();
    }
}
