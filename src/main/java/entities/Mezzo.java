package entities;


import enumerated.TipoMezzi;
import jakarta.persistence.*;

@Entity
@Table (name = "mezzi")
public class Mezzo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "capienza", nullable = false)
    private Integer capienza;

    @Column (name = "tipo", nullable = false)
    private TipoMezzi tipo;

    @Column (name = "in_servizio", nullable = false)
    private Boolean inServizio;

    @OneToOne
    @JoinColumn(name = "tratte_id", referencedColumnName = "id")
    private Tratta tratta;

    @ManyToOne
    @JoinColumn(name = "id_biglietto", referencedColumnName = "id")
    private Biglietto biglietto;


    public Mezzo() {

    }

    public Mezzo(Long id, Integer capienza, TipoMezzi tipo, Boolean inServizio) {
        this.id = id;
        this.capienza = capienza;
        this.tipo = tipo;
        this.inServizio = inServizio;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInServizio() {
        return inServizio;
    }

    public void setInServizio(Boolean inServizio) {
        this.inServizio = inServizio;
    }

    public TipoMezzi getTipo() {
        return tipo;
    }

    public void setTipo(TipoMezzi tipo) {
        this.tipo = tipo;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", tipo=" + tipo +
                ", inServizio=" + inServizio +
                '}';
    }
}
