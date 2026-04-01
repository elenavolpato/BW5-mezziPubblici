package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "acquisto")

public abstract class Acquisto {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column (name="location_acquisto")
    private LocationAcquisto location;
    //PuntoDiVendita

    @Column (name="data_emissione")
    private LocalDate dataEmissione;

    public Acquisto(){};

    public Acquisto(LocationAcquisto location, LocalDate dataEmissione){
        this.location = location;
        this.dataEmissione = dataEmissione;
    }

    public UUID getId() {        return id;    }

    public LocalDate getDataEmissione() {        return dataEmissione;    }
    public void setDataEmissione(LocalDate dataEmissione) {        this.dataEmissione = dataEmissione;    }

    public LocationAcquisto getLocationAcquisto() {        return location;    }
    public void setLocationAcquisto(LocationAcquisto location) {        this.location = location;    }


    @Override
    public String toString() {
        return "Acquisto{" +
                "id=" + id +
                ", location=" + location +
                ", emesso=" + dataEmissione +
                '}';
    }
}
