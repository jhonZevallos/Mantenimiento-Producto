package com.nttdata.bc.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditId")
    private Integer creditId;

    @NotNull(message = "El campo clientId es requerido.")
    @Column(name = "clientId")
    private Integer clientId;

    @NotNull(message = "El campo startDate es requerido.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "En el campo startDate, debe tener el siguiente formato YYYY-MM-DD")
    private LocalDate startDate;

    @NotNull(message = "El campo loan es requerido.")
    @Min(value = 1)
    @Column(name = "loan")
    private Integer loan; // cuenta

    @NotNull(message = "El campo monthlyPaymentDead es requerido.")
    @Column(name = "monthlyPaymentDead")
    private Integer monthlyPaymentDead; // Fecha de pago mensual

    @DecimalMin(value = "0.0", message = "El campo initialBalance debe tener un valor mínimo de '0.0'.")
    @Digits(integer = 10, fraction = 3, message = "El campo initialBalance tiene un formato no válido (#####.000).")
    @NotNull(message = "El campo initialBalance es requerido.")
    @Column(name = "initialBalance")
    private BigDecimal initialBalance; // Saldo inicial

    @DecimalMin(value = "0.0", message = "El campo currentBalance debe tener un valor mínimo de '0.0'.")
    @Digits(integer = 10, fraction = 3, message = "El campo currentBalance tiene un formato no válido (#####.000).")
    @NotNull(message = "El campo currentBalance es requerido.")
    @Column(name = "currentBalance")
    private BigDecimal currentBalance; // Saldo actual

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "updateddAt")
    private Instant updateddAt;
}
