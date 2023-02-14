package com.nttdata.bc.models;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CreditCards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditCardId")
    private Integer creditCardId;

    @NotNull(message = "El campo clientId es requerido.")
    @Column(name = "clientId")
    private Integer clientId;

    @Column(name = "cardNumber", nullable = false, length = 40)
    private String cardNumber; // número de la tarjeta

    @Column(name = "pin", nullable = false, length = 4)
    private String pin;

    @Column(name = "expirationDate", nullable = false, length = 5)
    private String expirationDate; // fecha de vencimiento

    @Column(name = "cardValidationCode", nullable = false, length = 3)
    private String cardValidationCode; // código de validación de la tarjeta

    @NotNull(message = "El campo monthlyCutoffDate es requerido.")
    @Column(name = "monthlyCutoffDate")
    private Integer monthlyCutoffDate; // Fecha de corte mensual

    @NotNull(message = "El campo monthlyPaymentDeadline es requerido.")
    @Column(name = "monthlyPaymentDeadline")
    private Integer monthlyPaymentDeadline; // Fecha límite de pago mensual

    @DecimalMin(value = "0.0", message = "El campo currentBalance debe tener un valor mínimo de '0.0'.")
    @Digits(integer = 10, fraction = 3, message = "El campo currentBalance tiene un formato no válido (#####.000).")
    @NotNull(message = "El campo currentBalance es requerido.")
    @Column(name = "currentBalance")
    private BigDecimal currentBalance; // Saldo actual

    @DecimalMin(value = "0.0", message = "El campo creditLimit debe tener un valor mínimo de '0.0'.")
    @Digits(integer = 10, fraction = 3, message = "El campo creditLimit tiene un formato no válido (#####.000).")
    @NotNull(message = "El campo creditLimit es requerido.")
    @Column(name = "creditLimit")
    private BigDecimal creditLimit; // Límite de crédito

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "updateddAt")
    private Instant updateddAt;
}
