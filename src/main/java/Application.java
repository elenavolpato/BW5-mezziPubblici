import dao.*;
import entities.Percorrenza;
import entities.User;
import enumerated.Periodo;
import enumerated.TipoUtente;
import jakarta.persistence.*;
import service.UtenzaService;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

public static void main(String[] args){
    EntityManager em = emf.createEntityManager();

    AcquistoDAO acquistoDAO = new AcquistoDAO(em);
    MezzoDAO mezzoDAO = new MezzoDAO(em);
    PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
    PuntoDiVenditaDAO distDAO = new PuntoDiVenditaDAO(em);
    TrattaDAO trattaDAO = new TrattaDAO(em);
    TesseraDAO tesseraDAO = new TesseraDAO(em);
    UserDAO userDAO = new UserDAO(em);


    UtenzaService service1 = new UtenzaService(userDAO,tesseraDAO,em);
    service1.registraDatiUtente("Giovanni","Giorgio",LocalDate.now(),TipoUtente.USER,Periodo.MENSILE);


    System.out.println("funziona");
    em.close();
    emf.close();
    }

}
