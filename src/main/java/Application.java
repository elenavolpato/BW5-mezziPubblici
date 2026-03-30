import dao.AcquistoDAO;
import entities.Biglietto;
import enumerated.LocationAcquisto;
import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        AcquistoDAO acquistoDAO = new AcquistoDAO(em);

        Biglietto b1 = new Biglietto(LocationAcquisto.AUTOMATICO);
        //acquistoDAO.save(b1);
    }


}
