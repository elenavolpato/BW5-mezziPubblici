package entities;

import enumerated.LocationAcquisto;
import enumerated.Periodo;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="abbonamenti")
@PrimaryKeyJoinColumn(name = "id_acquisto")

public class Abbonamento extends Acquisto {

    private Periodo periodo;

    public Abbonamento() {
        super();
    }

    public Abbonamento(LocationAcquisto location, LocalDate dataEmissione) {
        super(location, dataEmissione);
        this.periodo = periodo;
    }

    public Periodo getPeriodo() {        return periodo;    }
    public void setPeriodo(Periodo periodo) {        this.periodo = periodo;    }

    @Override
    public String toString() {
        return "Abbonamento{" +
               "id=" + getId() +
               ", location=" + getLocationAcquisto() +
               ", emesso=" + getDataEmissione() +
               ", periodo=" + periodo +
                '}';
    }
}

