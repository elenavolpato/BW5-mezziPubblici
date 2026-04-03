import entities.*;
import dao.*;
import enumerated.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import service.UtenzaService;

import java.time.LocalDate;

public class DataInitializer {
    private EntityManager em;

    public DataInitializer(EntityManager em) {
        this.em = em;
    }

    public void bootstrapData() {
        AcquistoDAO acquistoDAO = new AcquistoDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        PuntoDiVenditaDAO pvDAO = new PuntoDiVenditaDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        UserDAO userDAO = new UserDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        UtenzaService service = new UtenzaService(userDAO, tesseraDAO, em);


        try {
            // --- 1. USERS ---
            service.registraDatiUtente("Abdellah", "Bazi", LocalDate.now(), TipoUtente.USER);
            service.registraDatiUtente("Elena", "Volpato", LocalDate.now(), TipoUtente.ADMIN);

            // --- 2. PUNTI VENDITA ---
            DistributoreAutomatico pv1 = new DistributoreAutomatico("via Roma, 2", true);
            DistributoreAutomatico pv2 = new DistributoreAutomatico("Piazza Duomo", true);
            Rivenditore pv3 = new Rivenditore("Corso Buenos Aires 15", "Tabaccheria Rossi");
            Rivenditore pv4 = new Rivenditore("Corso Giolotti, 45", "Tabaccheria Giolotti");
            pvDAO.save(pv1); pvDAO.save(pv2); pvDAO.save(pv3); pvDAO.save(pv4);

            // --- 3. TRATTE ---
            Tratta t1 = new Tratta("centro milano", "F89", 60);
            Tratta t2 = new Tratta("Milano Centrale - Malpensa", "MXP1", 50);
            Tratta t3 = new Tratta("Roma Termini - Trastevere", "RM03", 15);
            Tratta t4 = new Tratta("Napoli Garibaldi - Sorrento", "CIRC1", 70);
            trattaDAO.saveTratta(t1); trattaDAO.saveTratta(t2);
            trattaDAO.saveTratta(t3); trattaDAO.saveTratta(t4);

            // --- 4. BIGLIETTI ---
            acquistoDAO.save(new Biglietto(pv1, LocalDate.of(2025, 12, 18)));
            acquistoDAO.save(new Biglietto(pv2, LocalDate.of(2025, 5, 10)));
            acquistoDAO.save(new Biglietto(pv1, LocalDate.now()));
            acquistoDAO.save(new Biglietto(pv4, LocalDate.of(2026, 01, 15)));

            // --- 5. ABBONAMENTI (Linked to the generated Tessera) ---
            acquistoDAO.save(new Abbonamento(pv1, LocalDate.of(2025, 10, 5), Periodo.SETTIMANALE));
            acquistoDAO.save(new Abbonamento(pv4, LocalDate.of(2026, 1, 1), Periodo.SETTIMANALE));
            acquistoDAO.save(new Abbonamento(pv3, LocalDate.of(2026, 2, 10), Periodo.MENSILE));
            acquistoDAO.save(new Abbonamento(pv2, LocalDate.of(2026, 3, 9), Periodo.MENSILE));

            // --- 6. MEZZI ---
            Mezzo m1 = new Mezzo(52, TipoMezzi.AUTOBUS, true, t1, null);
            Mezzo m2 = new Mezzo(150, TipoMezzi.TRAM, true, t2, null);
            Mezzo m3 = new Mezzo(80, TipoMezzi.AUTOBUS, false, t3, null);
            Mezzo m4 = new Mezzo(200, TipoMezzi.TRAM, true, t3, null);
            mezzoDAO.saveMezzo(m1); mezzoDAO.saveMezzo(m2); mezzoDAO.saveMezzo(m3); mezzoDAO.saveMezzo(m4);

            // --- 7. PERCORRENZA ---
            percorrenzaDAO.savePercorrenza(new Percorrenza(5, t4, m1));
            percorrenzaDAO.savePercorrenza(new Percorrenza(45, t2, m1));
            percorrenzaDAO.savePercorrenza(new Percorrenza(20, t4, m3));
            percorrenzaDAO.savePercorrenza(new Percorrenza(65, t1, m2));

            // --- 8. STATO MEZZO ---
            StatoMezzoDAO statoMezzoDAO = new StatoMezzoDAO(em);
            StatoMezzo sm1 = new StatoMezzo("Motore spacatto", LocalDate.of(2026, 2, 20), null, m3);
            statoMezzoDAO.saveStatoMezzo(sm1);


            System.out.println("Data Initialized successfully!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
