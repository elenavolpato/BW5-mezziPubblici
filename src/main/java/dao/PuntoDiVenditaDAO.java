package dao;

import entities.DistributoreAutomatico;
import entities.PuntoDiVendita;
import entities.Rivenditore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<PuntoDiVendita> getAll(){
       TypedQuery<DistributoreAutomatico> distributoriAttivi = em.createQuery("SELECT d FROM DistributoreAutomatico d WHERE d.attivo = true", DistributoreAutomatico.class);
       List<DistributoreAutomatico>  distributori =  distributoriAttivi.getResultList();
        TypedQuery<Rivenditore> rivenditori = em.createQuery("SELECT r FROM Rivenditore r ", Rivenditore.class);
        List<Rivenditore> rivenditoriL =  rivenditori.getResultList();
        List<PuntoDiVendita> all = Stream.concat(distributori.stream(),rivenditoriL.stream()).toList();
        return all;
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
