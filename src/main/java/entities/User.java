package entities;

import enumerated.TipoUtente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Utente")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    public User() {
    }

    public User(Long id, String nome, String cognome, LocalDate dataDiNascita, TipoUtente tipoUtente) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.tipoUtente = tipoUtente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String cognome;

    @Column (nullable = false)
    private LocalDate dataDiNascita;

    @Enumerated(EnumType.STRING)
    private TipoUtente tipoUtente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
