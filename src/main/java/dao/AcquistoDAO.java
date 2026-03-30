package dao;

import entities.Abbonamento;
import entities.Acquisto;
import entities.Biglietto;
import jakarta.persistence.*;

public class AcquistoDAO {

    private EntityManager em;
    public AcquistoDAO (EntityManager em){this.em = em;}

       public void save(Acquisto item){
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(item);
            t.commit();
            if(item instanceof Biglietto){
            System.out.println("Biglietto " + item.getId() + " acquistato");}
            else if( item instanceof Abbonamento){
                System.out.println("Abbonamento " + item.getId() + " acquistato");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
