package dao;

import entities.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Mezzo findUserById(Long id) {
        return entityManager.find(Mezzo.class, id);

    }

    public void saveUser(Mezzo mezzo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mezzo);
        transaction.commit();

    }

    public void removeUser(Mezzo mezzo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(mezzo);
        transaction.commit();
    }

}
