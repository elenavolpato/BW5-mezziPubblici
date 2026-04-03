package dao;

import entities.Percorrenza;
import entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class PercorrenzaDAO {

    private final EntityManager em;

    public PercorrenzaDAO(EntityManager em){this.em= em;}

    public void savePercorrenza(Percorrenza newPercorrenza){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newPercorrenza);

        transaction.commit();

        System.out.println("la percorrenza con id " + newPercorrenza.getId() + " è stata salvata");
    }

    public void removeFromPercorrenza(long percorrenzaID){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM Percorrenza p WHERE p.id = :percorrenzaID");
        query.setParameter("percorrenzaID" , percorrenzaID);

        query.executeUpdate();
        transaction.commit();

        System.out.println("percorrenza cancellata");
    }

    public Percorrenza cercaPercorrenzaPerID(long percorrenzaID){
        return em.find(Percorrenza.class, percorrenzaID);

    }

    public List<Percorrenza> percorrenzeInfoByMezzo(Long mezzoId) {
        TypedQuery<Percorrenza> query = em.createQuery(
                "SELECT p FROM Percorrenza p " +
                        "JOIN FETCH p.tratta " +
                        "JOIN FETCH p.mezzo " +
                        "WHERE p.mezzo.id = :mezzoId",
                Percorrenza.class
        );

        query.setParameter("mezzoId", mezzoId);

        return query.getResultList();
    }

    public double calcolaMediaTempoPerMezzo(Long mezzoId) {
        List<Percorrenza> lista = percorrenzeInfoByMezzo(mezzoId);

        if (lista.isEmpty()) return 0;

        double somma = 0;

        for (Percorrenza p : lista) {
            somma += p.getTempoMedioPerTratta();
        }

        return somma / lista.size();
    }


}
