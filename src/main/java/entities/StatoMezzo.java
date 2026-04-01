package entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stato_mezzo")
public class StatoMezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    @Column(name = "data_fine")
    private LocalDate dataFine;
    @Column(name = "motivo")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;


    protected StatoMezzo(){};

    public StatoMezzo(String motivo, LocalDate dataInizio, LocalDate dataFine, Mezzo mezzo ){
        this.motivo = motivo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.mezzo = mezzo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public Long getId() {
        return id;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "id=" + id +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", motivo='" + motivo + '\'' +
                ", mezzo=" + mezzo +
                '}';
    }
}