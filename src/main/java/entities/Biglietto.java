package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="biglietti")
@PrimaryKeyJoinColumn(name = "id")

public class Biglietto extends Acquisto {

    @Column(name = "data_ora_validazione", nullable = true)
    private LocalDateTime dataOraValidazione;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = true)
    private Mezzo mezzo;

    public Biglietto(){super();};

    public Biglietto(LocationAcquisto locationAcquisto, LocalDate dataEmissione) {
        super(locationAcquisto,dataEmissione);
    }

    public void validateOn(Mezzo mezzo) {
        this.mezzo = mezzo;
        this.setDataOraValidazione(LocalDateTime.now());
    }

    public Mezzo getMezzo() { return mezzo; }
    public void setMezzo(Mezzo mezzo) { this.mezzo = mezzo; }

    public LocalDateTime getDataOraValidazione() {        return dataOraValidazione;    }
    public void setDataOraValidazione(LocalDateTime dataOraValidazione) {
        this.dataOraValidazione = dataOraValidazione;
    }


    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + getId() +
                ", dataEmissione=" + getDataEmissione() +
                ", location=" + getLocationAcquisto() +
                ", validato=" +( dataOraValidazione != null ? dataOraValidazione : "Biglietto ancora non validato") +
                ", mezzoId=" + (getMezzo() != null ? getMezzo().getId() : "Non validato") +
                '}';
    }
}
