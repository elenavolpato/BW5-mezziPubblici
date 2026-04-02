package dao;

import entities.*;
import enumerated.Periodo;
import enumerated.TipoUtente;
import exceptions.NotFoundException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AcquistoDAO {

    private EntityManager em;
    public AcquistoDAO (EntityManager em){this.em = em;}

       public void save(Acquisto item){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(item);
            t.commit();
            if(item instanceof Biglietto){
            System.out.println("Biglietto " + item.getId() + " acquistato");
            }
            else if( item instanceof Abbonamento){
                System.out.println("Abbonamento " + item.getId() + " acquistato");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Acquisto findById(Long id){
        Acquisto found =  em.find(Acquisto.class, id);
        if(found == null) throw new NotFoundException(id.toString());
        return found;
    }

    public List<Abbonamento> listAllAbbonamentiByUser(Long userId) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.tessera.id = :id",
                Abbonamento.class
        );
        query.setParameter("id", userId);
        return query.getResultList();
    }


    public void acquistaAbbonamentoUserWithTessera(Long userId, Periodo periodo, PuntoDiVendita pv) {
        User user = em.find(User.class, userId);
        Tessera userTessera = user.getTessera();

        if (userTessera == null) {
            System.out.println("Error: User does not have a valid card!");
            return;
        }

        Abbonamento nuovoAbbonamento = new Abbonamento(pv, LocalDate.now(), periodo);
        nuovoAbbonamento.setTessera(userTessera);

        em.persist(nuovoAbbonamento);
    }


    public void deleteAcquistoById(Long id) {
        Acquisto found = findById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(found);
        t.commit();
        System.out.println("Ticket " + id + " è stato cancellato.");
    }
}
