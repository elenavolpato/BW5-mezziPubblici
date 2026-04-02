package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("distributore")
@Table(name = "distributori_automatici")
public class DistributoreAutomatico extends PuntoDiVendita{
    private boolean attivo;

   protected  DistributoreAutomatico(){}
    public DistributoreAutomatico(String indirizzo,boolean attivo) {
        super(indirizzo);
        this.attivo = attivo;
    }
    public boolean isAttivo() {
        return attivo;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico" +
                " con indirizzo " + super.getIndirizzo()  +
                " e id " + super.getId();
    }
}
