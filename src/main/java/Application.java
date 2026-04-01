import dao.PuntoDiVenditaDAO;
import entities.*;
import enumerated.Periodo;
import dao.AcquistoDAO;
import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

public static void main(String[] args){
    EntityManager em = emf.createEntityManager();
    AcquistoDAO acquistoDAO = new AcquistoDAO(em);
    DistributoreAutomaticoDao distributoreAutomaticoDao = new DistributoreAutomaticoDao(em);

    System.out.println("funziona");
    em.close();
    emf.close();
    }
}
