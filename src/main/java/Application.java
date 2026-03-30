import entities.Biglietto;
import entities.Mezzo;
import entities.Tratta;
import enumerated.TipoMezzi;
import dao.AcquistoDAO;
import dao.DistributoreAutomaticoDao;
import entities.Biglietto;
import entities.DistributoreAutomatico;
import enumerated.LocationAcquisto;
import jakarta.persistence.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");



public static void main(String[] args){

    Tratta tratta1 = new Tratta ("prova1","prova2",10);
    Biglietto biglietto = new Biglietto();
    Mezzo mezzo1 = new Mezzo (12,TipoMezzi.AUTOBUS,true,tratta1,biglietto);

    System.out.println(mezzo1);
}

        Biglietto b1 = new Biglietto(LocationAcquisto.AUTOMATICO);
        DistributoreAutomaticoDao distributoreAutomaticoDao = new DistributoreAutomaticoDao(em);
        DistributoreAutomatico d1 = new DistributoreAutomatico(true);
        Biglietto b1 = new Biglietto(LocationAcquisto.AUTOMATICO,d1);
        distributoreAutomaticoDao.save(d1);
        acquistoDAO.save(b1);

        //acquistoDAO.save(b1);
    }


}
