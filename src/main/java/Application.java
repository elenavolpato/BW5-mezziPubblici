import dao.PercorrenzaDAO;
import dao.TrattaDAO;
import entities.*;
import enumerated.TipoMezzi;
import dao.AcquistoDAO;
import dao.DistributoreAutomaticoDao;
import entities.Biglietto;
import enumerated.LocationAcquisto;
import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

}
