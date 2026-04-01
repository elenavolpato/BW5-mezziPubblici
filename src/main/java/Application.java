import dao.*;
import entities.*;
import enumerated.TipoMezzi;
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
    PuntoDiVenditaDAO pvDAO = new PuntoDiVenditaDAO(em);
    TrattaDAO trattaDAO = new TrattaDAO(em);
    UserDAO userDAO = new UserDAO(em);

    // ---------------------- USER ------------------

    User u1 = new User("Joao", "Da Silva", LocalDate.of(2002, 3, 12), TipoUtente.USER);
    User u2 = new User("Mario", "Rossi", LocalDate.of(1985, 7, 22), TipoUtente.USER);
    User u3 = new User("Elena", "Bianchi", LocalDate.of(1998, 11, 5), TipoUtente.ADMIN);
    User u4 = new User("Luca", "Verdi", LocalDate.of(2005, 1, 30), TipoUtente.USER);
    userDAO.saveUser(u2); userDAO.saveUser(u3); userDAO.saveUser(u4);
    userDAO.saveUser(u1);


    // ------------------------- TRATTA -----------------------------

    Tratta t1 = new Tratta("centro milano", "F89", 60);
    Tratta t2 = new Tratta("Milano Centrale - Malpensa", "MXP1", 50);
    Tratta t3 = new Tratta("Roma Termini - Trastevere", "RM03", 15);
    Tratta t4 = new Tratta("Napoli Garibaldi - Sorrento", "CIRC1", 70);
    trattaDAO.saveTratta(t2); trattaDAO.saveTratta(t3); trattaDAO.saveTratta(t4);
    trattaDAO.saveTratta(t1);

    //Tratta foundT1 = trattaDAO.cercaTrattaPerId(1L);
    //Tratta foundT2 = trattaDAO.cercaTrattaPerId("a1b2c3d4-e5f6-4g7h-8i9j-0k1l2m3n4o5p");
    //Tratta foundT3 = trattaDAO.cercaTrattaPerId("b2c3d4e5-f6g7-h8i9-j0k1-l2m3n4o5p6q");
    //Tratta foundT4 = trattaDAO.cercaTrattaPerId("c3d4e5f6-g7h8-i9j0-k1l2-m3n4o5p6q7r");


    // ------------------------- PUNTO DI VENDITA --------------------------

    DistributoreAutomatico pv1 = new DistributoreAutomatico("via Roma, 2", true);
    DistributoreAutomatico pv2 = new DistributoreAutomatico("Piazza Duomo", true);
    Rivenditore pv3 = new Rivenditore("Tabaccheria Rossi", "Corso Buenos Aires 15");
    DistributoreAutomatico pv4 = new DistributoreAutomatico("Stazione Garibaldi - Binario 4", false);
    pvDAO.save(pv2); pvDAO.save(pv3); pvDAO.save(pv4);
    pvDAO.save(pv1);

    //PuntoDiVendita foundPv1 = pvDAO.findById("768d306e-9a65-4f6a-bac4-6ea4d9f800bd");


    // ----------------------------------- ACQUISTO ---------------------------------

    Biglietto b1 = new Biglietto(pv1,LocalDate.of(2025, 12,18));
    Biglietto b2 = new Biglietto(pv2, LocalDate.of(2025, 5, 10));
    Biglietto b3 = new Biglietto(pv1, LocalDate.now());
    Biglietto b4 = new Biglietto(pv2, LocalDate.of(2026, 01, 15));

    acquistoDAO.save(b2); acquistoDAO.save(b3); acquistoDAO.save(b4);
    acquistoDAO.save(b1);
    //Acquisto foundB1 = acquistoDAO.findById(1L);




    // ----------------------------------- MEZZO ------------------------------

    Mezzo m1 = new Mezzo(52, TipoMezzi.AUTOBUS, true, t1, b1);
    Mezzo m2 = new Mezzo(150, TipoMezzi.TRAM, true, t2, b2);
    Mezzo m3 = new Mezzo(80, TipoMezzi.AUTOBUS, false, t3, b3); // In manutenzione
    Mezzo m4 = new Mezzo(200, TipoMezzi.TRAM, true, t4, b4);
    mezzoDAO.saveMezzo(m2); mezzoDAO.saveMezzo(m3); mezzoDAO.saveMezzo(m4);
    mezzoDAO.saveMezzo(m1);

    //Mezzo foundM2 = mezzoDAO.findMezzoById(2L);
    //Mezzo foundM3 = mezzoDAO.findMezzoById(3L);
    //Mezzo foundM1 = mezzoDAO.findMezzoById(1L);




    // --------------------------- PERCORRENZA ----------------------------

    Percorrenza p1 = new Percorrenza(5, t1, m1);
    Percorrenza p2 = new Percorrenza(45, t2, m2);
    Percorrenza p3 = new Percorrenza(20, t3, m3);
    Percorrenza p4 = new Percorrenza(65, t4, m2);

    percorrenzaDAO.savePercorrenza(p2); percorrenzaDAO.savePercorrenza(p3); percorrenzaDAO.savePercorrenza(p4);
    percorrenzaDAO.savePercorrenza(p1);

    em.close();
    emf.close();
    }

}
