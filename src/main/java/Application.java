import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");

    public static void main(String[] args) {
        System.out.println("funziona");
    }


}
