package com.ntt.bc.model;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredito;
    private LocalDate fechaInicio = LocalDate.now();
    @Nonnull
    private int cuotas;
    @Nonnull
    private LocalDate fechaPago;
    @Nonnull
    private Double saldoInicial;
    @Nonnull
    private Double saldoActual;

}
