package com.nttdata.bc.repositories;

import com.nttdata.bc.models.CreditCard;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditCardRepository implements PanacheRepositoryBase<CreditCard, Integer> {

}
