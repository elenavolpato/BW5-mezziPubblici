package dao;

import entities.Mezzo;
import entities.Tessera;
import entities.User;
import enumerated.Periodo;
import enumerated.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

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
    }

    public void removeUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
    }

    public User creazioneUtenza(String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente,Periodo periodo){
        User user = new User(nome,cognome,dataDiNascita,tipoUtente);
        creazioneTessera(periodo);

        return user;
    }


    public void creazioneTessera(Periodo periodo){
        Tessera newTessera = new Tessera();
        calcoloPeriodoValidita(periodo,newTessera);

    }
    public void calcoloPeriodoValidita(Periodo periodo, Tessera newTessera){


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


    }
}
