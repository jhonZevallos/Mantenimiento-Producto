package com.ntt.bc.model;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarjeta_debito")
public class TarjetaDebito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;
    @NotEmpty
    private String numTarjeta;
    @NotEmpty
    private String pin;
    @Nonnull
    private LocalDate fechaVencimiento;
    @NotEmpty
    private String codValidacion;
}
