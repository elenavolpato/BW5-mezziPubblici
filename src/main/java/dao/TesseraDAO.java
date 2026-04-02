package dao;

import entities.Mezzo;
import entities.Tessera;
import entities.User;
import enumerated.Periodo;
import enumerated.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class TesseraDAO {

    private final EntityManager entityManager;

    public TesseraDAO (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Tessera findTesseraById(Long id) {
        return entityManager.find(Tessera.class, id);
    }

    public void saveTessera(Tessera tessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        entityManager.persist(tessera);
    }

    public void removeTessera(Tessera tessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        entityManager.remove(tessera);
    }


}
