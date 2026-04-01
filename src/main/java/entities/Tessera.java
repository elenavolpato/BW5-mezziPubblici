package entities;

import enumerated.TipoUtente;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tessera")
@PrimaryKeyJoinColumn(name = "id")
public class Tessera extends User {

    public Tessera() {

    }

    public Tessera(String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente, LocalDate scadenza) {
        super( nome, cognome, dataDiNascita, tipoUtente);
        this.scadenza = scadenza;
    }

    @Column(nullable = false)
    private LocalDate scadenza;

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "scadenza=" + scadenza +
                "} " + super.toString();
    }
}

