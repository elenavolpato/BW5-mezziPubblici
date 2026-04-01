import dao.*;
import entities.Percorrenza;
import entities.User;
import enumerated.TipoUtente;
import jakarta.persistence.*;

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
    UserDAO userDAO = new UserDAO(em);

    // add user

    User u1 = new User("Nome", "Cognome", LocalDate.of(2002, 03, 12), TipoUtente.USER);
    userDAO.saveUser(u1);

    System.out.println("funziona");
    em.close();
    emf.close();
    }

}
