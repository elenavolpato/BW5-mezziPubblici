package dao;

import entities.Mezzo;
import entities.Tessera;
import entities.Tratta;
import entities.User;
import enumerated.Periodo;
import enumerated.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Mezzo findUserById(Long id) {
        return entityManager.find(Mezzo.class, id);
    }

    public void saveUser(User user) {


        entityManager.persist(user);

        System.out.println("creato user");
    }
    public void saveTessera(Tessera tessera) {
        entityManager.persist(tessera);
        System.out.println("creata tessera");
    }

    public void removeUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        entityManager.remove(user);

    }
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u",
                User.class
        );
        return query.getResultList();
    }


}
