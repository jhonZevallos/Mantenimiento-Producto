package com.nttdata.bc.services.impl;

import java.time.Instant;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.nttdata.bc.clients.IClientRestClient;
import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.Client;
import com.nttdata.bc.models.Credit;
import com.nttdata.bc.repositories.CreditRepository;
import com.nttdata.bc.services.ICreditService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreditServiceImpl implements ICreditService {
    @Inject
    Logger logger;

    @Inject
    private CreditRepository repository;

    @Inject
    @RestClient
    IClientRestClient clientRestClient;

    @Override
    public Credit insert(Credit obj) {
        Client client = clientRestClient.fintById(obj.getClientId());

        obj.setIsActive(true);
        obj.setCreatedAt(Instant.now());
        this.repository.persist(obj);

        return obj;
    }

    @Override
    public Credit update(Credit obj) {
        Credit credit = this.findById(obj.getCreditId());
        credit.setCurrentBalance(obj.getCurrentBalance());
        credit.setUpdateddAt(Instant.now());

        return credit;
    }

    @Override
    public List<Credit> findAll() {
        return this.repository.listAll();
    }

    @Override
    public Credit findById(Integer id) {
        Credit credit = this.repository.findById(id);
        if (credit == null) {
            throw new NotFoundException("El crédito con id: " + id.toString() + ", no existe.");
        }

        return this.repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        Credit credit = this.findById(id);
        credit.setIsActive(false);
    }

}
