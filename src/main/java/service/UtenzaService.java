package service;

import dao.TesseraDAO;
import dao.UserDAO;
import entities.Tessera;
import entities.User;
import enumerated.Periodo;
import enumerated.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class UtenzaService {

    private final UserDAO userDAO;
    private final TesseraDAO tesseraDAO;
    private final EntityManager entityManager;

    public UtenzaService(UserDAO userDAO, TesseraDAO tesseraDAO, EntityManager entityManager) {
        this.userDAO = userDAO;
        this.tesseraDAO = tesseraDAO;
        this.entityManager = entityManager;
    }

    public void registraDatiUtente(String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente) {

        User newUser = null;
        Tessera newTessera = null;

        switch (tipoUtente) {
            case USER:


                newTessera = new Tessera(nome, cognome, dataDiNascita, tipoUtente);
                newTessera.setScadenza(LocalDate.now().plusYears(1));
                tesseraDAO.saveTessera(newTessera);
                break;

            case ADMIN:
                newUser = new User(nome, cognome, dataDiNascita, tipoUtente);
                newUser.setTipoUtente(TipoUtente.ADMIN);
                userDAO.saveUser(newUser);
                break;

            default:
                System.out.println("scelta non valida");
        }
    }

    public void updateUser(Long id,String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente) {


        String queryModifica = "UPDATE Tessera t " +
                "SET t.nome = :nome, " +
                "t.cognome = :cognome, " +
                "t.dataDiNascita = :dataDiNascita, " +
                "t.tipoUtente = :tipoUtente " +
                "WHERE t.id = :id";

        entityManager.createQuery(queryModifica)
                .setParameter("nome", nome)
                .setParameter("cognome", cognome)
                .setParameter("dataDiNascita", dataDiNascita)
                .setParameter("tipoUtente", tipoUtente)
                .setParameter("id", id)
                .executeUpdate();

    }

    public void updateScadenzaTessera(Long id) {
        Tessera tessera = entityManager.find(Tessera.class, id);
        if (tessera == null) {
            throw new IllegalArgumentException("Tessera non trovata");
        }
        tessera.setScadenza(LocalDate.now().plusYears(1));
    }

}
