package entities;

import enumerated.Periodo;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="abbonamenti")
@PrimaryKeyJoinColumn(name = "id_acquisto")


public class Abbonamento extends Acquisto {
    @Enumerated(EnumType.STRING)
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    public Abbonamento() {
        super();
    }

    public Abbonamento(PuntoDiVendita puntoDiVendita, LocalDate dataEmissione, Periodo periodo) {
        super(puntoDiVendita, dataEmissione);
        this.periodo = periodo;
        // Tessera is left null here, to be set later via setter
    }

    public void setTessera(Tessera tessera) {        this.tessera = tessera;    }
    public Tessera getTessera() {        return tessera;    }

    public Periodo getPeriodo() {        return periodo;    }
    public void setPeriodo(Periodo periodo) {        this.periodo = periodo;    }

    @Override
    public String toString() {
        return "Abbonamento{" +
               "id=" + getId() +
               ", location=" + getPuntoDiVendita() +
               ", emesso=" + getDataEmissione() +
               ", periodo=" + periodo +
                '}';
    }
}

