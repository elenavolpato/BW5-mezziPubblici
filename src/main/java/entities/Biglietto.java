package entities;

import dao.DistributoreAutomaticoDao;
import enumerated.LocationAcquisto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="biglietti")

public class Biglietto extends Acquisto {
    private boolean validato;
    private LocalDate dataEmissione;

    @Column(name = "data_ora_validazione")
    private LocalDateTime dataOraValidazione;

    public Biglietto(){
        super();
    };

    public Biglietto(LocationAcquisto locationAcquisto, DistributoreAutomatico distributoreAutomatico) {
        super(locationAcquisto,distributoreAutomatico);
        this.validato = super.isValidato();
        this.dataEmissione = super.getDataEmissione();
    }
    public void valida(Mezzo mezzo) {
        this.dataOraValidazione = LocalDateTime.now();
        this.setMezzo(mezzo); // Setting the FK in the parent class
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + super.getId() +
                ", locationAcquisto=" + super.getLocationAcquisto() +
                ", validato=" + validato +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
