package entities;

import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="biglietti")
@PrimaryKeyJoinColumn(name = "id")

public class Biglietto extends Acquisto {
    @Column(name = "data_ora_validazione")
    private LocalDateTime dataOraValidazione;

    public Biglietto(){super();};

    public Biglietto(LocationAcquisto locationAcquisto) {
        super(locationAcquisto);
    }

    public void valida(Mezzo mezzo) {
        this.dataOraValidazione = LocalDateTime.now();
        this.setMezzo(mezzo); // Setting the FK in the parent class
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
                ", validato=" + isValidato() +
                ", dataEmissione=" + getDataEmissione() +
                ", dataValidazione=" + dataOraValidazione +
                ", mezzoId=" + (getMezzo() != null ? getMezzo().getId() : "Non validato") +
                '}';
    }
}
