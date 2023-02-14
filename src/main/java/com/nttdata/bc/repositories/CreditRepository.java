package com.nttdata.bc.repositories;

import com.nttdata.bc.models.Credit;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditRepository implements PanacheRepositoryBase<Credit, Integer> {

}
