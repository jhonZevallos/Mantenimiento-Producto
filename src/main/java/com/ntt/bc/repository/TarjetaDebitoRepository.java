package com.ntt.bc.repository;

import com.ntt.bc.model.TarjetaDebito;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarjetaDebitoRepository implements PanacheRepository<TarjetaDebito> {

}
