package dao;

import entities.PuntoDiVendita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class PuntoDiVenditaDAO {
    private EntityManager em;
    public PuntoDiVenditaDAO(EntityManager em){this.em = em;}
    public void save(PuntoDiVendita pv){
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        em.persist(pv);
        ts.commit();
        System.out.println("punto di vendita con indirizzo " +pv.getIndirizzo()+ " aggiunto al database");
    }
    public PuntoDiVendita findById(String id){
        PuntoDiVendita found = em.find(PuntoDiVendita.class, UUID.fromString(id));
        return found;
    }
    public void deleteById(String id){
        PuntoDiVendita found = em.find(PuntoDiVendita.class, UUID.fromString(id));
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        em.remove(found);
        ts.commit();
        System.out.println("punto di vendita con indirizzo " +found.getIndirizzo()+ " rimosso al database");
    }
}
