package dao;

import entities.Abbonamento;
import entities.Acquisto;
import entities.Biglietto;
import entities.PuntoDiVendita;
import exceptions.NotFoundException;
import jakarta.persistence.*;

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

    public Acquisto findById(String id){
        Acquisto found =  em.find(Acquisto.class, UUID.fromString(id));
        if(found == null) throw new NotFoundException(id);
        return found;
    }

    public void deleteAcquistoById(String id) {
        Acquisto found = findById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(found);
        t.commit();
        System.out.println("Ticket " + id + " è stato cancellato.");
    }
}
