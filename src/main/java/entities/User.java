package entities;

import enumerated.TipoUtente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Utente")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    public User() {
    }

    public User(String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.tipoUtente = tipoUtente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String cognome;

    @Column (nullable = false)
    private LocalDate dataDiNascita;

    @Enumerated(EnumType.STRING)
    private TipoUtente tipoUtente;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {        this.id = id;    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public TipoUtente getTipoUtente() {
        return tipoUtente;
    }
    public void setTipoUtente(TipoUtente tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", tipoUtente=" + tipoUtente +
                '}';
    }
}
