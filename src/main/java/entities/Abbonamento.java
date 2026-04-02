package entities;

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

    public Abbonamento(PuntoDiVendita puntoDiVendita, LocalDate dataEmissione) {
        super(puntoDiVendita, dataEmissione);
        this.periodo = periodo;
    }

    // TODO prendi la id della tessera

    public Periodo getPeriodo() {        return periodo;    }
    public void setPeriodo(Periodo periodo) {        this.periodo = periodo;    }

    @Override
    public String toString() {
        return "Abbonamento{" +
               "id=" + getId() +
               ", location=" + getPuntoDiVendita() +
               ", emesso=" + getDataEmissione() +
               ", periodo=" + periodo +
//                ", tessera=" + id tessera +
                '}';
    }
}

