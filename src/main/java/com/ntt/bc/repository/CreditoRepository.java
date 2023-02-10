package com.ntt.bc.repository;

import com.ntt.bc.model.Credito;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditoRepository implements PanacheRepository<Credito> {

}
