package entities;


import jakarta.persistence.*;

import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "n_tratte_percorse")
    private int nTrattePercorse;

    @Column(name = "tempo_effettivo")
    // sarà la somma del tempo previsto per tratta  per  le n tratte percorse.
    private int tempoEffettivo;

    @Column(name = "tempo_medio_per_tratta")
    private double tempoMedioPerTratta;


    // one to one
    @OneToOne
    @JoinColumn(name = "tratta_id") //FK
    private Tratta tratta;

    // many to one perchè il mezzo puo percorre piu volte
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    protected Percorrenza(){};


    public Percorrenza(int nTrattePercorse, Tratta tratta, Mezzo mezzo){

        Random random = new Random();
        int totale = 0;

        for (int i = 0 ; i < nTrattePercorse; i++){
            int variazione = random.nextInt(-6 , 7);
            int tempoSingoloPerTratta = Math.max(1, tratta.getTempoPrevisto() + variazione);
            totale += tempoSingoloPerTratta;
        }
        this.nTrattePercorse = nTrattePercorse;
        this.tratta = tratta;
        this.mezzo = mezzo;
        this.tempoEffettivo = totale;
        this.tempoMedioPerTratta = (double) totale / nTrattePercorse;

    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public UUID getId() {
        return id;
    }

    public double getTempoMedioPerTratta() {
        return tempoMedioPerTratta;
    }

    public int getnTrattePercorse() {
        return nTrattePercorse;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }


    @Override
    public String toString() {
        return "Percorrenza{" +
                "id=" + id +
                ", nTrattePercorse=" + nTrattePercorse +
                ", tempoEffettivo=" + tempoEffettivo + " min " +
                ", tempoMedioPerTratta=" + tempoMedioPerTratta + " min " +
                ", tratta=" + tratta +
                ", mezzo=" + mezzo +
                '}';
    }
}
