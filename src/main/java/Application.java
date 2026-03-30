import entities.Biglietto;
import entities.Mezzo;
import entities.Tratta;
import enumerated.TipoMezzi;
import dao.AcquistoDAO;
import dao.DistributoreAutomaticoDao;
import entities.Abbonamento;
import entities.Biglietto;
import entities.DistributoreAutomatico;
import enumerated.LocationAcquisto;
import enumerated.Periodo;
import jakarta.persistence.*;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");



public static void main(String[] args){

    Tratta tratta1 = new Tratta ("prova1","prova2",10);
    Biglietto biglietto = new Biglietto();
    Mezzo mezzo1 = new Mezzo (12,TipoMezzi.AUTOBUS,true,tratta1,biglietto);

    System.out.println(mezzo1);
}

        DistributoreAutomaticoDao distributoreAutomaticoDao = new DistributoreAutomaticoDao(em);
        DistributoreAutomatico d1 = new DistributoreAutomatico(true);
        Biglietto b1 = new Biglietto(LocationAcquisto.AUTOMATICO,d1);
        distributoreAutomaticoDao.save(d1);
        acquistoDAO.save(b1);
DistributoreAutomatico foundD = distributoreAutomaticoDao.findById(1223456);
        acquistoDAO.save(b1);


        Abbonamento ab = new Abbonamento(LocationAcquisto.RIVENDITORE, Periodo.MENSILE, LocalDate.now(), "Elena", "Blabla", LocalDate.of(1900,01,01), foundD);
        acquistoDAO.save(ab);
    }
}
