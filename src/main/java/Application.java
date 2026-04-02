import dao.*;
import entities.*;

import enumerated.TipoMezzi;
import enumerated.TipoUtente;
import enumerated.Periodo;

import exceptions.NotFoundException;
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
        try {
            DataInitializer initializer = new DataInitializer(em);

            System.out.println("Starting database seeding...");
            initializer.bootstrapData();
            System.out.println("Database is ready!");


        } catch (Exception e) {
            System.err.println("An error occurred during startup:");
            e.printStackTrace();
        } finally {
            em.close();
        }

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
                if(risposta.equals("y") || risposta.equals("yes") || risposta.equals("si")){
                    risp = true;
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
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        boolean risp = false;
        long tesseraId = 0;
        while(!risp){
            System.out.println("dammi il tuo numero della tessera...");
            try{
                 tesseraId = Long.parseLong(scanner.nextLine());
                 if(tesseraId<0) throw new IllegalArgumentException("il numero tessera non può essere minore di 0");
                 if(tesseraDAO.findTesseraById(tesseraId) == null) throw new NotFoundException("");
                 risp = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Inserire un numero valido.");
            } catch (IllegalArgumentException | NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        Tessera found = tesseraDAO.findTesseraById(tesseraId);
        if(found.getScadenza().isBefore(LocalDate.now())){
            boolean risp1 = false;
            while(!risp1){
                try{
                    System.out.println("tessera scaduta, vuoi rinnovarla?y/n");
                    String risposta = scanner.nextLine();
                    if(risposta.equals("y") || risposta.equals("yes") || risposta.equals("si")){
                        risp1 = true;
                        UserDAO userDAO = new UserDAO(em);
                        UtenzaService service = new UtenzaService(userDAO,tesseraDAO,em);
                        EntityTransaction transaction = em.getTransaction();
                        transaction.begin();
                        service.updateScadenzaTessera(tesseraId);
                        transaction.commit();
                        System.out.println("tessera rinnovata con successo");
                    } else if (risposta.equals("n") || risposta.equals("no") ) {
                        inizioScanner();
                        risp1 = true;
                    } else throw new IllegalArgumentException("risposta non valida, riprova");
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }

        }
        System.out.println("la tessera ha scadenza al : "+ found.getScadenza());
        boolean risp2 = false;
        while(!risp2){
            try{
                System.out.println("vuoi tornare al menù?y/n");
                String risposta = scanner.nextLine();
                if(risposta.equals("y") || risposta.equals("yes") || risposta.equals("si")){
                    inizioScanner();
                    risp2 = true;
                } else if (risposta.equals("n") || risposta.equals("no") ) {
                    scanner.close();
                    risp2 = true;
                } else throw new IllegalArgumentException("risposta non valida, riprova");
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        String RESET  = "\u001B[0m";
        String RED    = "\u001B[31m";

        System.out.println(RED+"░░░░░ ░   ░ ░░░░░ ░   ░ ░   ░  ░░░░    ░   ░ "+RESET);
        System.out.println(RED+"  ░   ░   ░ ░   ░ ░░  ░ ░  ░  ░        ░   ░ "+RESET);
        System.out.println(RED+"  ░   ░░░░░ ░░░░░ ░ ░ ░ ░░    ░░░░     ░   ░ "+RESET);
        System.out.println(RED+"  ░   ░   ░ ░   ░ ░  ░░ ░  ░      ░          "+RESET);
        System.out.println(RED+"  ░   ░   ░ ░   ░ ░   ░ ░   ░ ░░░░     ░   ░ "+RESET);


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
    TesseraDAO tesseraDAO = new TesseraDAO(em);
    UserDAO userDAO = new UserDAO(em);


    UtenzaService service = new UtenzaService(userDAO,tesseraDAO,em);
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    service.registraDatiUtente(nome,cognome,dataFinale,TipoUtente.USER);
    transaction.commit();
    AcquistoDAO acquistoDAO = new AcquistoDAO(em);
    Abbonamento abbonamento = new Abbonamento(puntoDiVendita,LocalDate.now(),periodo);
    acquistoDAO.save(abbonamento);
        System.out.println("premere '0' per terminare oppure un tasto qualsiasi per tornare alla home");
        String risposta2 = scanner.nextLine();
        if(!risposta2.equals("0")) inizioScanner();
        else scanner.close();
    }
}
