package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Abbonamento {

    public LocalDate setDataScadenza(LocalDate dataEmissione){
        if()
            LocalDate dataScadenza = dataEmissione.plusDays(30);
        return dataScadenza;
    }



}
