package dao;


import entities.StatoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class StatoMezzoDAO {

    private final EntityManager em;

    public StatoMezzoDAO(EntityManager em){this.em = em;}

    public void saveStatoMezzo(StatoMezzo newStatoMezzo){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newStatoMezzo);

        transaction.commit();

        System.out.println("lo stato del mezzo " + newStatoMezzo.getMezzo().getId() + " è stato salvato");
    }

    public void removeFromStatoMezzo(long statoMezzoID){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM StatoMezzo s WHERE s.id = :statoMezzoID");
        query.setParameter("statoMezzoID" , statoMezzoID);

        query.executeUpdate();
        transaction.commit();

        System.out.println("lo stato è stato cancellato");
    }
    public void statoMezzoInfo(long statoMezzoID) {
        StatoMezzo statoMezzo = em.find(StatoMezzo.class, statoMezzoID);

        if (statoMezzo != null) {
            System.out.println("ID stato mezzo: " + statoMezzo.getId());
            System.out.println("Motivo: " + statoMezzo.getMotivo());
            System.out.println("Data inizio: " + statoMezzo.getDataInizio());
            System.out.println("Data fine: " + statoMezzo.getDataFine());
            System.out.println("ID mezzo: " + statoMezzo.getMezzo().getId());
        } else {
            System.out.println("Nessuno stato mezzo trovato con id: " + statoMezzoID);
        }
    }

    public void statoMezzoInfoByMezzoId(long mezzoId) {
        TypedQuery<StatoMezzo> query = em.createQuery(
                "SELECT s FROM StatoMezzo s WHERE s.mezzo.id = :mezzoId ORDER BY s.dataInizio DESC",
                StatoMezzo.class
        );
        query.setParameter("mezzoId", mezzoId);

        List<StatoMezzo> stati = query.getResultList();

        if (stati.isEmpty()) {
            System.out.println("Nessuno stato mezzo trovato per il mezzo con id: " + mezzoId);
        } else {
            for (StatoMezzo statoMezzo : stati) {
                System.out.println("ID stato mezzo: " + statoMezzo.getId());
                System.out.println("Motivo: " + statoMezzo.getMotivo());
                System.out.println("Data inizio: " + statoMezzo.getDataInizio());
                System.out.println("Data fine: " + statoMezzo.getDataFine());
                System.out.println("ID mezzo: " + statoMezzo.getMezzo().getId());
                System.out.println("------------------------");
            }
        }
    }

    public StatoMezzo findByStatoMezzoId(Long statoMezzoID) {
        return  em.find(StatoMezzo.class, statoMezzoID );
    }
}
