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




    protected Tratta(){};

    public Tratta(String zonaDiPartenza, String capolinea, int tempoPrevisto){
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
    }

    public UUID getId() {
        return id;
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
                ", tempoPrevisto=" + tempoPrevisto + " min " +
                '}';
    }


}
