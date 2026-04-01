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
    em.close();
    emf.close();
    }
}
