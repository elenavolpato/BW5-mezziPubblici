import dao.*;
import entities.*;

import enumerated.TipoMezzi;
import enumerated.TipoUtente;
import enumerated.Periodo;

import jakarta.persistence.*;
import service.UtenzaService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;



public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW5-mezziPubblici");
    public static Scanner scanner = new Scanner(System.in);
    private static  final  EntityManager em = emf.createEntityManager();


public static void main(String[] args){
   


    AcquistoDAO acquistoDAO = new AcquistoDAO(em);
    MezzoDAO mezzoDAO = new MezzoDAO(em);
    PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
    PuntoDiVenditaDAO pvDAO = new PuntoDiVenditaDAO(em);
    TrattaDAO trattaDAO = new TrattaDAO(em);
    TesseraDAO tesseraDAO = new TesseraDAO(em);
    UserDAO userDAO = new UserDAO(em);


    UtenzaService service = new UtenzaService(userDAO,tesseraDAO,em);
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    service.registraDatiUtente("Abdellah","Bazi",LocalDate.now(),TipoUtente.USER);
    service.registraDatiUtente("Elena","Volpato",LocalDate.now(),TipoUtente.ADMIN);
    service.updateUser(1L,"Abdellah","Bazzzzzzi",LocalDate.now(),TipoUtente.USER);
    service.updateScadenzaTessera(1L);
    transaction.commit();


    // ---------------------- USER ------------------

    User u1 = new User("Nome", "Cognome", LocalDate.of(2002, 03, 12), TipoUtente.USER);
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

    //Tratta foundT1 = trattaDAO.cercaTrattaPerId("3f68ae38-a8d5-4b87-8008-15c555e83d2c");
    //Tratta foundT2 = trattaDAO.cercaTrattaPerId("a1b2c3d4-e5f6-4g7h-8i9j-0k1l2m3n4o5p");
    //Tratta foundT3 = trattaDAO.cercaTrattaPerId("b2c3d4e5-f6g7-h8i9-j0k1-l2m3n4o5p6q");
    //Tratta foundT4 = trattaDAO.cercaTrattaPerId("c3d4e5f6-g7h8-i9j0-k1l2-m3n4o5p6q7r");




    // ------------------------- PUNTO DI VENDITA --------------------------

    DistributoreAutomatico pv1 = new DistributoreAutomatico("via Roma, 2", true);
    DistributoreAutomatico pv2 = new DistributoreAutomatico("Piazza Duomo", true);
    Rivenditore pv3 = new Rivenditore("Corso Buenos Aires 15", "Tabaccheria Rossi");
    Rivenditore pv4 = new Rivenditore("Corso Giolotti, 45", "Tabaccheria Giolotti");
     pvDAO.save(pv2); pvDAO.save(pv3); pvDAO.save(pv4);
    pvDAO.save(pv1);
    //PuntoDiVendita foundPv1 = pvDAO.findById("768d306e-9a65-4f6a-bac4-6ea4d9f800bd");


    

    // ----------------------------------- ACQUISTO ---------------------------------

    Biglietto b1 = new Biglietto(pv1,LocalDate.of(2025, 12,18));
    Biglietto b2 = new Biglietto(pv2, LocalDate.of(2025, 5, 10));
    Biglietto b3 = new Biglietto(pv1, LocalDate.now());
    Biglietto b4 = new Biglietto(pv4, LocalDate.of(2026, 01, 15));
     acquistoDAO.save(b2); acquistoDAO.save(b3); acquistoDAO.save(b4);
    acquistoDAO.save(b1);
    //Acquisto foundB1 = acquistoDAO.findById(1L);

    Abbonamento a1 = new Abbonamento(pv1, LocalDate.of(2025, 10,5 ),Periodo.SETTIMANALE);
    Abbonamento a2 = new Abbonamento(pv4, LocalDate.of(2026,1,1),Periodo.SETTIMANALE);
    Abbonamento a3 = new Abbonamento(pv3, LocalDate.of(2026, 2, 10),Periodo.MENSILE);
    Abbonamento a4 = new Abbonamento(pv2, LocalDate.of(2026,3,9),Periodo.MENSILE);

    acquistoDAO.save(a1);acquistoDAO.save(a2);acquistoDAO.save(a3); acquistoDAO.save(a4);
  
  


    // ----------------------------------- MEZZO ------------------------------

    Mezzo m1 = new Mezzo(52, TipoMezzi.AUTOBUS, true, t1, b2);
    Mezzo m2 = new Mezzo(150, TipoMezzi.TRAM, true, t2, b1);
    Mezzo m3 = new Mezzo(80, TipoMezzi.AUTOBUS, false, t3, b4); // In manutenzione
    Mezzo m4 = new Mezzo(200, TipoMezzi.TRAM, true, t3, b3);
    mezzoDAO.saveMezzo(m1); mezzoDAO.saveMezzo(m2); mezzoDAO.saveMezzo(m3); mezzoDAO.saveMezzo(m4);

    //Mezzo foundM2 = mezzoDAO.findMezzoById(2L);
    //Mezzo foundM3 = mezzoDAO.findMezzoById(3L);

    //Mezzo foundM1 = mezzoDAO.findMezzoById(1L);




    // ----------------------------------- PERCORRENZA ------------------------------

    Percorrenza p1 = new Percorrenza(5, t4, m1);
    Percorrenza p2 = new Percorrenza(45, t2, m1);
    Percorrenza p3 = new Percorrenza(20, t4, m3);
    Percorrenza p4 = new Percorrenza(65, t1,m2);
    percorrenzaDAO.savePercorrenza(p2); percorrenzaDAO.savePercorrenza(p3); percorrenzaDAO.savePercorrenza(p4);
    percorrenzaDAO.savePercorrenza(p1);

    em.close();
    emf.close();
  
    }
    public static void inizioScanner(){
        String RESET  = "\u001B[0m";
        String YELLOW = "\u001B[33m";

        System.out.println(YELLOW+"░  ░  ░░░░░  ░     ░  ░░░░░"+RESET);
        System.out.println(YELLOW+"░  ░  ░   ░  ░░   ░░  ░    "+RESET);
        System.out.println(YELLOW+"░░░░  ░   ░  ░  ░  ░  ░░░░ "+RESET);
        System.out.println(YELLOW+"░  ░  ░   ░  ░     ░  ░    "+RESET);
        System.out.println(YELLOW+"░  ░  ░░░░░  ░     ░  ░░░░░"+RESET);
        PuntoDiVenditaDAO puntoDiVenditaDAO = new PuntoDiVenditaDAO(em);
        boolean risp = false;
        List<PuntoDiVendita> list = puntoDiVenditaDAO.getAll();
        int risposta = 0;
        while(!risp){
            System.out.println("scegli pure il metodo di acquisto vicino alle tue zone digitando il numero desiderato...");
            for(int i = 0; i<list.size();i++){
                System.out.println(i+1 + " - " + list.get(i));
            }
            try{
                risposta = Integer.parseInt(scanner.nextLine());
                if(risposta>list.size() || risposta<1) throw new IndexOutOfBoundsException("numero non selezionabile, riprova con uno nela lista");
                risp = true;
            }
            catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e){
                System.out.println("risposta non valida, riprova");
            }
        }
        System.out.println("hai scelto : "+list.get(risposta-1));
        PuntoDiVendita puntoScelto = list.get(risposta-1);
        richiestaTessera(puntoScelto);

    }
    public static void richiestaTessera(PuntoDiVendita puntoScelto){
    boolean risp = false;
        while(!risp){
            try{
                System.out.println("possiedi già una tessera? y/n");
                String risposta = scanner.nextLine();
                // todo : creare funzione per la verifica della tessera
                if(risposta.equals("y") || risposta.equals("yes") || risposta.equals("si")){
                    risp = true;
                    System.out.println("perfetto dammi il tuo numero della tessera...");
                    controlloTessera(puntoScelto);
                } else if (risposta.equals("n") || risposta.equals("no") ) {
                    risp = true;
                    acquisto(puntoScelto);
                } else throw new IllegalArgumentException("risposta non valida, riprova");
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

    }
    public static void controlloTessera(PuntoDiVendita puntoScelto){
    }
    public static void acquisto(PuntoDiVendita puntoScelto){
        boolean risp = false;
        int risposta = 0;
        String RESET  = "\u001B[0m";
        String GREEN  = "\u001B[32m";

        System.out.println(GREEN+"░░░░░  ░   ░  ░░░░   ░░░░  "+RESET);
        System.out.println(GREEN+"░      ░   ░ ░    ░ ░    ░ "+RESET);
        System.out.println(GREEN+"░░░░   ░░░░░ ░    ░ ░░░░░  "+RESET);
        System.out.println(GREEN+"    ░  ░   ░ ░    ░ ░      "+RESET);
        System.out.println(GREEN+"░░░░░  ░   ░  ░░░░  ░      "+RESET);
        while(!risp){
            System.out.println("ok allora puoi acquistare un nuovo biglietto o un'abbonamento...\n" +
                    "cosa scegli :\n1 - biglietto\n2 - abbonamento");
            try{
                risposta = Integer.parseInt(scanner.nextLine());
                if(risposta == 1) {
                    creazioneBiglietto(puntoScelto);
                    risp = true;
                }
                else if(risposta == 2) {
                    creazioneAbbonamento(puntoScelto);
                    risp = true;
                }
                else  throw new IndexOutOfBoundsException("numero non selezionabile, riprova con uno nela lista");

            }
            catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }

    }
    public static void creazioneBiglietto(PuntoDiVendita puntoDiVendita){
        System.out.println("creazione del biglietto in corso...");
        Biglietto biglietto = new Biglietto(puntoDiVendita,LocalDate.now());
        AcquistoDAO acquistoDAO = new AcquistoDAO(em);
        acquistoDAO.save(biglietto);
        System.out.println("premere '0' per terminare oppure un tasto qualsiasi per tornare alla home");
        String risp = scanner.nextLine();
        if(!risp.equals("0")) inizioScanner();
        else scanner.close();
    }
    public static void creazioneAbbonamento(PuntoDiVendita puntoDiVendita){
    boolean risp = false;
    int risposta = 0;
    boolean rispNome = false;
    String nome = "";
    boolean rispCognome = false;
    String cognome = "";
    boolean rispData = false;
    String data = "";
    LocalDate dataFinale = LocalDate.now();
        System.out.println("per avere un'abbonamanto bisogna avere una tessera... creiamone una!");
    while(!rispNome){
        System.out.println("dammi il tuo nome...");
        try{
            nome = scanner.nextLine();
            if(nome.isBlank()) throw new IllegalArgumentException("il nome non può essere vuoto, riprova");
            rispNome = true;
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while(!rispCognome){
        System.out.println("dammi il tuo cognome...");
        try{
             cognome = scanner.nextLine();
            if(cognome.isBlank()) throw new IllegalArgumentException("il cognome non può essere vuoto, riprova");
            rispCognome = true;
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    while (!rispData) {
            System.out.println("dammi la tua data di nascita (es-> 2002-04-02)...");
            try {
                data = scanner.nextLine();

                if (data.isBlank()) throw new IllegalArgumentException("la data non può essere vuota, riprova");

                // controlla il formato
                String[] parti = data.split("-");
                if (parti.length != 3) throw new IllegalArgumentException("formato non valido, usa YYYY-MM-DD");

                int anno = Integer.parseInt(parti[0]);
                int mese = Integer.parseInt(parti[1]);
                int giorno = Integer.parseInt(parti[2]);

                // controlla l'anno
                int annoCorrente = LocalDate.now().getYear();
                if (anno < 1900 || anno > annoCorrente)
                    throw new IllegalArgumentException("anno non valido, deve essere tra 1900 e " + annoCorrente);

                // controlla il mese
                if (mese < 1 || mese > 12)
                    throw new IllegalArgumentException("mese non valido, deve essere tra 1 e 12");

                // controlla il giorno
                LocalDate dataNascita = LocalDate.of(anno, mese, giorno);
                dataFinale = dataNascita;

                // controlla che non sia nel futuro
                if (dataNascita.isAfter(LocalDate.now()))
                    throw new IllegalArgumentException("la data non può essere nel futuro");

                // controlla età minima
                if (dataNascita.isAfter(LocalDate.now().minusYears(16)))
                    throw new IllegalArgumentException("età troppo bassa, devi avere almeno 16 anni");
                rispData = true;

            } catch (NumberFormatException e) {
                System.out.println("inserisci solo numeri nel formato YYYY-MM-DD");
            } catch (DateTimeException e) {
                System.out.println("data non esistente (es. 30 febbraio non esiste), riprova");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    while(!risp){
            System.out.println("che tipo di abbonamento?\n1 - mensile\n2 - settimanale");
            try{
                risposta = Integer.parseInt(scanner.nextLine());
                if(risposta == 1) {
                    risp = true;
                }
                else if(risposta == 2) {
                    risp = true;
                }
                else  throw new IndexOutOfBoundsException("numero non selezionabile, riprova con uno nela lista");

            }
            catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }
    Periodo periodo = risposta == 1? Periodo.MENSILE : Periodo.SETTIMANALE;
    UserDAO userDAO = new UserDAO(em);
    Tessera tessera = new Tessera(nome,cognome,dataFinale,TipoUtente.USER);
    userDAO.saveTessera(tessera);
    AcquistoDAO acquistoDAO = new AcquistoDAO(em);
    Abbonamento abbonamento = new Abbonamento(puntoDiVendita,LocalDate.now(),periodo);
    acquistoDAO.save(abbonamento);
        System.out.println("premere '0' per terminare oppure un tasto qualsiasi per tornare alla home");
        String risposta2 = scanner.nextLine();
        if(!risposta2.equals("0")) inizioScanner();
        else scanner.close();
    }
  
  
  

}
