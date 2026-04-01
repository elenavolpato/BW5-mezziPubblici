package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "acquisto")

public abstract class Acquisto {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="punto_di_vendita")
    private PuntoDiVendita puntoDiVendita;

    @Column (name="data_emissione")
    private LocalDate dataEmissione;

    public Acquisto(){};

    public Acquisto(PuntoDiVendita puntoDiVendita, LocalDate dataEmissione){
        this.puntoDiVendita = puntoDiVendita;
        this.dataEmissione = dataEmissione;
    }

    public long getId() {        return id;    }

    public LocalDate getDataEmissione() {        return dataEmissione;    }
    public void setDataEmissione(LocalDate dataEmissione) {        this.dataEmissione = dataEmissione;    }

    public PuntoDiVendita getPuntoDiVendita() {        return puntoDiVendita;    }
    public void setPuntoDiVendita(PuntoDiVendita puntoDiVendita) {        this.puntoDiVendita = puntoDiVendita;    }


    @Override
    public String toString() {
        return "Acquisto{" +
                "id=" + id +
                ", punto di vendita=" + puntoDiVendita +
                ", emesso=" + dataEmissione +
                '}';
    }
}
