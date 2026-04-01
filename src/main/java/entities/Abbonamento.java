package entities;

import enumerated.LocationAcquisto;
import enumerated.Periodo;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="abbonamenti")

public class Abbonamento extends Acquisto {

    private Periodo periodo;

    @Column (name="data_di_scadenza")
    private LocalDate scadenza;

    private String nome;
    private String cognome;

    @Column(name="data_di_nascita")
    private LocalDate dataDiNascita;
    private long tessera;

    //@OneToOne
    @Column(name="id_utente")
    private long idUtente;




    public Abbonamento(){super();}

    public Abbonamento(LocationAcquisto locationAcquisto, Periodo periodo, LocalDate scadenza, String nome, String cognome, LocalDate dataDiNascita){
        super(locationAcquisto);
        this.periodo = Periodo.SETTIMANALE;
        this.scadenza = setScadenza(getDataEmissione());
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.idUtente = 123456; // TODO: collegare con tabella user
        this.tessera = setTessera();
    }

    public LocalDate setScadenza(LocalDate dataEmissione){
        LocalDate dataScadenza = dataEmissione.plusYears(1);
        return dataScadenza;
    }
    public LocalDate getScadenza() { return scadenza; }

    public long setTessera(){        return System.currentTimeMillis();    }
    public long getTessera() {        return tessera;    }

    public LocalDate getDataDiNascita() {        return dataDiNascita;    }
    public void setDataDiNascita(LocalDate dataDiNascita) {         this.dataDiNascita = dataDiNascita;    }

    public String getNome() {        return nome;    }
    public void setNome(String nome) {        this.nome = nome;    }

    public String getCognome() {        return cognome;    }
    public void setCognome(String cognome) {        this.cognome = cognome;    }

    public Periodo getPeriodo() {        return periodo;    }
    public void setPeriodo(Periodo periodo) {        this.periodo = periodo;    }

    public long getIdUtente() {        return idUtente;    }
    public void setIdUtente(long idUtente) {        this.idUtente = idUtente;    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + super.getId() +
                "periodo=" + periodo +
                ", scadenza=" + scadenza +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", tessera=" + tessera +
                ", idUtente=" + idUtente +
                '}';
    }
}
