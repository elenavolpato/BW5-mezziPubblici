import entities.*;
import enumerated.Periodo;
import enumerated.TipoMezzi;
import dao.AcquistoDAO;
import dao.DistributoreAutomaticoDao;
import entities.Abbonamento;
import entities.Biglietto;
import enumerated.LocationAcquisto;
import enumerated.Periodo;
import jakarta.persistence.*;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

public static void main(String[] args){
    EntityManager em = emf.createEntityManager();
    AcquistoDAO acquistoDAO = new AcquistoDAO(em);
    DistributoreAutomaticoDao distributoreAutomaticoDao = new DistributoreAutomaticoDao(em);

    System.out.println("funziona");
    }
}
