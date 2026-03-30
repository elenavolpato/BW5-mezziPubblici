import entities.Biglietto;
import entities.Mezzo;
import entities.Tratta;
import enumerated.TipoMezzi;
import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");



public static void main(String[] args){

    Tratta tratta1 = new Tratta ("prova1","prova2",10);
    Biglietto biglietto = new Biglietto();
    Mezzo mezzo1 = new Mezzo (12,TipoMezzi.AUTOBUS,true,tratta1,biglietto);

    System.out.println(mezzo1);
}



}
