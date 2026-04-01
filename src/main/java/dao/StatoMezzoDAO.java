package dao;


import entities.StatoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;


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

    public StatoMezzo findByStatoMezzoId(Long statoMezzoID) {
        return  em.find(StatoMezzo.class, statoMezzoID );
    }
}
