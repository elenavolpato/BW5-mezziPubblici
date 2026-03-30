import dao.AcquistoDAO;
import dao.DistributoreAutomaticoDao;
import entities.Biglietto;
import entities.DistributoreAutomatico;
import enumerated.LocationAcquisto;
import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        AcquistoDAO acquistoDAO = new AcquistoDAO(em);
        DistributoreAutomaticoDao distributoreAutomaticoDao = new DistributoreAutomaticoDao(em);
        DistributoreAutomatico d1 = new DistributoreAutomatico(true);
        Biglietto b1 = new Biglietto(LocationAcquisto.AUTOMATICO,d1);
        distributoreAutomaticoDao.save(d1);
        acquistoDAO.save(b1);

        //acquistoDAO.save(b1);
    }


}
