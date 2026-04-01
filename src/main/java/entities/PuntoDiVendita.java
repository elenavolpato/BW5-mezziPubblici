package entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_venditore", discriminatorType = DiscriminatorType.STRING)
@Table(name = "punto_vendita")
public abstract class PuntoDiVendita {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String indirizzo;

    protected PuntoDiVendita() {}

    public PuntoDiVendita(String indirizzo){
        if(indirizzo.isBlank()) throw new IllegalArgumentException("indirizzo non puo essere vuoto");
        this.indirizzo = indirizzo;
    }

    public Long getId() {
        return id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public String toString() {
        return "PuntoDiVendita{" +
                "id=" + id +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
