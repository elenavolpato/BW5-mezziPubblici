package dao;

import entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezzoDAO {
    private final EntityManager entityManager;


    public MezzoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void saveMezzo(Mezzo mezzo) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mezzo);
        transaction.commit();
    }

    public Mezzo findMezzoById(Long id) {
        return  entityManager.find(Mezzo.class, id );
    }

    public void deleteMezzo(Mezzo mezzo) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(mezzo);
        transaction.commit();
    }
}
