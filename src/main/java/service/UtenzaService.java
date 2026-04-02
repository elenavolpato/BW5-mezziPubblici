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

    public void registraDatiUtente(String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente, Periodo periodo){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        User user = new User(nome,cognome,dataDiNascita,tipoUtente);



        Tessera newTessera = new Tessera(nome,cognome,dataDiNascita,tipoUtente,LocalDate.now());
        System.out.println(newTessera);
        switch(periodo) {
            case MENSILE:
                newTessera.setScadenza(LocalDate.now().plusMonths(1));
                break;
            case SETTIMANALE:
                newTessera.setScadenza(LocalDate.now().plusDays(7));
                break;
            default:
                System.out.println("scelta non valida");
        }

        tesseraDAO.saveTessera(newTessera);
        transaction.commit();

    }
}
