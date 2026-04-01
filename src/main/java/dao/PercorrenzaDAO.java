package dao;

import entities.Percorrenza;
import entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class PercorrenzaDAO {

    private final EntityManager em;

    public PercorrenzaDAO(EntityManager em){this.em= em;}

    public void savePercorrenza(Tratta newPercorrenza){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newPercorrenza);

        transaction.commit();

        System.out.println("la percorrenza con id " + newPercorrenza.getId() + " è stata salvata");
    }

    public void removeFromPercorrenza(int percorrenzaID){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM Percorrenza p WHERE p.id = :percorrenzaID");
        query.setParameter("percorrenzaID" , percorrenzaID);

        query.executeUpdate();
        transaction.commit();

        System.out.println("percorrenza cancellata");
    }

    public Percorrenza cercaPercorrenzaPerID(String percorrenzaID){
        Percorrenza found = em.find(Percorrenza.class, UUID.fromString(percorrenzaID));
        return found;

    }


}
