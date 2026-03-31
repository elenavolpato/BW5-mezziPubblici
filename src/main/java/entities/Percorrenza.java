package entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "percorrenze")

public class Percorrenza {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratta;

    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;

    @Column(name = "tempo_effettivo")
    private long tempoEffettivo;

    public void calcolaTempoEffettivo() {
        this.tempoEffettivo = java.time.Duration.between(dataOraInizio, dataOraFine).toMinutes();
    }
}