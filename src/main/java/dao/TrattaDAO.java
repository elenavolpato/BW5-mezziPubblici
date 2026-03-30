package dao;

import entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class TrattaDAO {
    private final EntityManager em;

    public TrattaDAO(EntityManager em){this.em= em;}

    public void saveTratta(Tratta newTratta){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newTratta);

        transaction.commit();

        System.out.println("la tratta con id " + newTratta.getId() + " è stata salvata");
    }

    public void removeFromTratta(int trattaID){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM Tratta t WHERE t.id = :trattaID");
        query.setParameter("trattaID" , trattaID);

        query.executeUpdate();
        transaction.commit();

        System.out.println("tratta cancellata");

    }



}
