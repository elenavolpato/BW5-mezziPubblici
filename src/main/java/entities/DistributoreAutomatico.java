package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Distributori_automatici")
public class DistributoreAutomatico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice_distributore")
    private long id;
    private boolean attivo;

    protected   DistributoreAutomatico(){};
    public  DistributoreAutomatico(Boolean attivo){
        this.attivo = attivo;
    }

    public long getId() {
        return id;
    }
}
