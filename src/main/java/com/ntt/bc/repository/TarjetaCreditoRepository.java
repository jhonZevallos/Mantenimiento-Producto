package com.ntt.bc.repository;

import com.ntt.bc.model.TarjetaCredito;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarjetaCreditoRepository implements PanacheRepository<TarjetaCredito> {

}
