package entities;

import enumerated.TipoStato;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stati_mezzo")
public class InManutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoStato stato;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @Column(length = 500)
    private String note;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    // --- Constructors ---

    public InManutenzione() {}

    public InManutenzione(Mezzo mezzo, TipoStato stato, LocalDate dataInizio) {
        this.mezzo = mezzo;
        this.stato = stato;
        this.dataInizio = dataInizio;
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public StatoMezzo getStato() { return stato; }
    public void setStato(StatoMezzo stato) { this.stato = stato; }

    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }

    public LocalDate getDataFine() { return dataFine; }
    public void setDataFine(LocalDate dataFine) { this.dataFine = dataFine; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Mezzo getMezzo() { return mezzo; }
    public void setMezzo(Mezzo mezzo) { this.mezzo = mezzo; }
}

