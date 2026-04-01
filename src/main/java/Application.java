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

    DistributoreAutomatico d1 = new DistributoreAutomatico(true);
    Biglietto b1 = new Biglietto(LocationAcquisto.AUTOMATICO,d1);
    distributoreAutomaticoDao.save(d1);
    acquistoDAO.save(b1);
    DistributoreAutomatico foundD = distributoreAutomaticoDao.findById(1);
    acquistoDAO.save(b1);

    Abbonamento ab = new Abbonamento(LocationAcquisto.RIVENDITORE,foundD, Periodo.MENSILE, LocalDate.now(), "Carolina", "Santos" , LocalDate.of(1900,01,01));
    acquistoDAO.save(ab);
    }
}
