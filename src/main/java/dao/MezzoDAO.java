package dao;

import entities.DistributoreAutomatico;
import entities.Mezzo;
import entities.PuntoDiVendita;
import entities.Rivenditore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Stream;

public class MezzoDAO {
    private final EntityManager entityManager;


    public MezzoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void saveMezzo(Mezzo mezzo) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mezzo);
        transaction.commit();
    }

    public Mezzo findMezzoById(Long id) {
        return  entityManager.find(Mezzo.class, id );
    }

    public void deleteMezzo(Mezzo mezzo) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(mezzo);
        transaction.commit();
    }
public List<Mezzo> getAllMezzi(){
        TypedQuery<Mezzo> query = entityManager.createQuery("SELECT m FROM Mezzo m " , Mezzo.class);
        return query.getResultList();
}
    public void updateMezzo(Mezzo mezzo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(mezzo);
        transaction.commit();
    }

}
