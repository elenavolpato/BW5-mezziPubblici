package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (name="location_acquisto")
    private LocationAcquisto locationAcquisto;

    private boolean validato;

    @Column (name="data_emissione")
    private LocalDate dataEmissione;

    public Biglietto(){};
    public Biglietto(LocationAcquisto locationAcquisto){
        this.locationAcquisto = LocationAcquisto.AUTOMATICO;
        // TODO: prendere il codice della location
        this.validato = false;
        this.dataEmissione = LocalDate.now();
    }

    public UUID getId() {        return id;    }

    public LocalDate getDataEmissione() {        return dataEmissione;    }
    public void setDataEmissione(LocalDate dataEmissione) {        this.dataEmissione = dataEmissione;    }

    public LocationAcquisto getLocationAcquisto() {        return locationAcquisto;    }
    public void setLocationAcquisto(LocationAcquisto locationAcquisto) {        this.locationAcquisto = locationAcquisto;    }

    public boolean isValidato() {       return validato;    }
    public void setValidato(boolean validato) {        this.validato = validato;    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", locationAcquisto=" + locationAcquisto +
                ", validato=" + validato +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
