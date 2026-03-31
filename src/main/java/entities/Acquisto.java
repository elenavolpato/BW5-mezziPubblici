package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "acquisti")

public abstract class Acquisto {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column (name="location_acquisto")
    private LocationAcquisto location;

    private boolean validato = false;

    @Column (name="data_emissione")
    private LocalDate dataEmissione;

    @ManyToOne
    @JoinColumn(name = "distributore_id",nullable = true)
    private DistributoreAutomatico distributoreAutomatico;

//    @ManyToOne
//    @JoinColumn(name = "rivenditore_id", nullable = true)
//    private Rivenditore rivenditore;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = true)
    private Mezzo mezzo;

    public Acquisto(){};
    public Acquisto(LocationAcquisto locationAcquisto,DistributoreAutomatico dist/*, Rivenditore riv*/){
        this.location = location;
        this.validato = false; // Always false at purchase
        this.dataEmissione = LocalDate.now();

        if (location == LocationAcquisto.AUTOMATICO) {
            this.distributoreAutomatico = dist;
            //this.rivenditore = null;
        } else {
           // this.rivenditore = riv;
            this.distributoreAutomatico = null;
        }
    }

    public UUID getId() {        return id;    }

    public LocalDate getDataEmissione() {        return dataEmissione;    }
    public void setDataEmissione(LocalDate dataEmissione) {        this.dataEmissione = dataEmissione;    }

    public LocationAcquisto getLocationAcquisto() {        return location;    }
    public void setLocationAcquisto(LocationAcquisto location) {        this.location = location;    }

    public boolean isValidato() {       return validato;    }
    public void setValidato(boolean validato) {        this.validato = validato;    }

//    public Rivenditore getRivenditore() { return rivenditore; }
//    public void setRivenditore(Rivenditore rivenditore) { this.rivenditore = rivenditore; }

    public DistributoreAutomatico getDistributoreAutomatico() { return distributoreAutomatico; }
    public void setDistributoreAutomatico(DistributoreAutomatico dist) { this.distributoreAutomatico = dist; }

    public Mezzo getMezzo() { return mezzo; }
    public void setMezzo(Mezzo mezzo) { this.mezzo = mezzo; }

    @Override
    public String toString() {
        return "Acquisto{" +
                "id=" + id +
                ", location=" + location +
                ", validato=" + validato +
                ", emesso=" + dataEmissione +
                ", mezzo=" + (mezzo != null ? mezzo.getId() : "N/A") +
                '}';
    }
}
