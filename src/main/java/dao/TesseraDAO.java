package dao;

import entities.Mezzo;
import entities.Tessera;
import entities.User;
import enumerated.Periodo;
import enumerated.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;

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
        entityManager.persist(tessera);
    }

    public void removeTessera(Tessera tessera) {
        entityManager.remove(tessera);
    }





}
