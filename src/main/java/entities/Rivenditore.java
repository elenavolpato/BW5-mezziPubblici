package entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("rivenditore")
@Table(name = "rivenditori")
public class Rivenditore extends PuntoDiVendita{
    @Column(name = "nome_rivenditore")
    public String nome;

    protected Rivenditore(){}
    public Rivenditore(String indirizzo,String nome){
        super(indirizzo);
        if(nome.isBlank()) throw  new IllegalArgumentException("nome non puo essere vuoto");
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Rivenditore di " + nome + '\'' +
                " con indirizzo = " + super.getIndirizzo()  +
                " e id = " + super.getId() ;
    }
}
