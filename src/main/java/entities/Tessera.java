package entities;

import enumerated.TipoUtente;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tessera")
@PrimaryKeyJoinColumn(name = "id")

public class Tessera extends User {
    @Column(nullable = false)
    private LocalDate scadenza;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private User user;

    public Tessera() {  }

    public Tessera(String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente) {
        super( nome, cognome, dataDiNascita, tipoUtente);
        this.scadenza = LocalDate.now().plusYears(1);
    }


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

