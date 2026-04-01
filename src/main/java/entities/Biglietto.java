package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="biglietti")
@PrimaryKeyJoinColumn(name = "id_acquisto")

public class Biglietto extends Acquisto {
    @Column(name = "data_ora_validazione", nullable = true)
    private LocalDateTime dataOraValidazione;

    public Biglietto(){super();};

    public Biglietto(LocationAcquisto locationAcquisto, DistributoreAutomatico distributoreAutomatico) {
        super(locationAcquisto,distributoreAutomatico);
    }

      public void validateOn(Mezzo mezzo) {
        if (this.isValidato()) {
            System.out.println("Ticket already used!");
            return;
        }
        this.setValidato(true); // Updates the field in Acquisto
        this.setMezzo(mezzo);     // Updates the FK in Acquisto
        this.setDataOraValidazione(LocalDateTime.now()); // Specific to Biglietto
    }

    public LocalDateTime getDataOraValidazione() {        return dataOraValidazione;    }
    public void setDataOraValidazione(LocalDateTime dataOraValidazione) {
        this.dataOraValidazione = dataOraValidazione;
    }


    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + getId() +
                ", dataEmissione=" + getDataEmissione() +
                ", dataValidazione=" + dataOraValidazione +
                ", mezzoId=" + (getMezzo() != null ? getMezzo().getId() : "Non validato") +
                '}';
    }
}
