package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public abstract class Acquisto {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column (name="location_acquisto")
    private LocationAcquisto locationAcquisto;

    private boolean validato = false;

    @Column (name="data_emissione")
    private LocalDate dataEmissione;

    @ManyToOne
    @JoinColumn(name = "distributore_id",nullable = true)
    private DistributoreAutomatico distributoreAutomatico;

    public Acquisto(){};
    public Acquisto(LocationAcquisto locationAcquisto,DistributoreAutomatico distributoreAutomatico){
        this.locationAcquisto = LocationAcquisto.AUTOMATICO;
        // TODO: prendere il codice della location
        this.validato = validato;
        this.dataEmissione = LocalDate.now();
        this.distributoreAutomatico = distributoreAutomatico;
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
        return "Acquisto{" +
                "id=" + id +
                ", locationAcquisto=" + locationAcquisto +
                ", validato=" + validato +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
