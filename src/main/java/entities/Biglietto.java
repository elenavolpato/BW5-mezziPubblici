package entities;

import dao.DistributoreAutomaticoDao;
import enumerated.LocationAcquisto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="biglietti")

public class Biglietto extends Acquisto {
    private boolean validato;
    private LocalDate dataEmissione;

    public Biglietto(){
        super();
    };

    public Biglietto(LocationAcquisto locationAcquisto, DistributoreAutomatico distributoreAutomatico) {
        super(locationAcquisto,distributoreAutomatico);
        this.validato = super.isValidato();
        this.dataEmissione = super.getDataEmissione();
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
