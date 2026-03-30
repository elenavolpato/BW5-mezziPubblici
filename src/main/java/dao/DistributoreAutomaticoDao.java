package dao;

import entities.Acquisto;
import entities.DistributoreAutomatico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DistributoreAutomaticoDao {
    private EntityManager em;

    protected  DistributoreAutomaticoDao(){}
    public DistributoreAutomaticoDao(EntityManager em){
        this.em = em;
    }
    public void save(DistributoreAutomatico d){
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        em.persist(d);
        ts.commit();
        System.out.println("distributore "+d.getId() + " salvato nel database");
    }

    public DistributoreAutomatico findById(long id) {
        return em.find(DistributoreAutomatico.class, id);

    }

}
