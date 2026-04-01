package dao;

import entities.Mezzo;
import entities.User;
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

    public void saveUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        // TODO funzione che crea la tessera

    }

    public void removeUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
    }

}
