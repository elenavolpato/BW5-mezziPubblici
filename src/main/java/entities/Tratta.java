package entities;


import jakarta.persistence.*;

import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "tratte")
public class Tratta {


    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "zona_di_partenza")
    private String zonaDiPartenza;
    @Column(name = "capolinea")
    private String capolinea;
    @Column(name = "tempo_previsto")
    private int tempoPrevisto;
    @Column(name = "tempo_effettivo")
    private int tempoEffettivo;



    protected Tratta(){};

    public Tratta(String zonaDiPartenza, String capolinea, int tempoPrevisto){
        Random random = new Random();
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
        this.tempoEffettivo = tempoPrevisto - random.nextInt(0, 6);
    }

    public UUID getId() {
        return id;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public int getTempoPrevisto() {
        return tempoPrevisto;
    }


    public String getZonaDiPartenza() {
        return zonaDiPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }


    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaDiPartenza='" + zonaDiPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }
}
