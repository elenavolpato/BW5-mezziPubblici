import dao.AcquistoDAO;
import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

public static void main(String[] args){
    EntityManager em = emf.createEntityManager();
    AcquistoDAO acquistoDAO = new AcquistoDAO(em);

    System.out.println("funziona");
    em.close();
    emf.close();
    }
}
