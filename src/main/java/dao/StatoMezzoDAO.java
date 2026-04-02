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

    public StatoMezzo findByStatoMezzoId(Long statoMezzoID) {
        return  em.find(StatoMezzo.class, statoMezzoID );
    }
}
