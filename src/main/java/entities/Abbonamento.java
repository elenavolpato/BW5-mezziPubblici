package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="abbonamenti")
public class Abbonamento extends Acquisto {
    @Id
    @GeneratedValue
    @Column(name="id_abbonamento")
    private long idAbbonamento;

   //@OneToOne




//    public LocalDate setDataScadenza(LocalDate dataEmissione){
//        if()
//            LocalDate dataScadenza = dataEmissione.plusDays(30);
//        return dataScadenza;
//    }



}
