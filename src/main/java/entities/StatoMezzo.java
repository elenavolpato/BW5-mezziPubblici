package entities;

import enumerated.TipoStato;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stato_mezzo")
public class StatoMezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoStato stato; // Enum: IN_SERVIZIO, IN_MANUTENZIONE

    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo; // This creates the connection to your vehicle
}