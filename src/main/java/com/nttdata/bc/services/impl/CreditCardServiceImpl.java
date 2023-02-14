package com.nttdata.bc.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.nttdata.bc.clients.IClientRestClient;
import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.Client;
import com.nttdata.bc.models.CreditCard;
import com.nttdata.bc.repositories.CreditCardRepository;
import com.nttdata.bc.services.ICreditCardService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreditCardServiceImpl implements ICreditCardService {
    @Inject
    Logger logger;

    @Inject
    private CreditCardRepository repository;

    @Inject
    @RestClient
    IClientRestClient clientRestClient;

    @Override
    public CreditCard insert(CreditCard obj) {
        Client client = clientRestClient.fintById(obj.getClientId());

        obj.setCardNumber(this.generateCardNumber());
        obj.setPin(this.generatePin());
        obj.setExpirationDate(this.generateExpirationDate());
        obj.setCardValidationCode(this.generateCardValidationCode());
        obj.setCurrentBalance(obj.getCreditLimit());
        obj.setIsActive(true);
        obj.setCreatedAt(Instant.now());
        this.repository.persist(obj);

        return obj;
    }

    @Override
    public CreditCard update(CreditCard obj) {
        CreditCard creditCard = this.findById(obj.getCreditCardId());

        if (obj.getPin() != null)
            creditCard.setPin(obj.getPin());
        creditCard.setCurrentBalance(obj.getCurrentBalance());
        creditCard.setCreditLimit(obj.getCreditLimit());
        creditCard.setUpdateddAt(Instant.now());

        return creditCard;
    }

    @Override
    public List<CreditCard> findAll() {
        return this.repository.listAll();
    }

    @Override
    public CreditCard findById(Integer id) {
        CreditCard creditCard = this.repository.findById(id);
        if (creditCard == null) {
            throw new NotFoundException("La tarjeta de crédito con id: " + id.toString() + ", no existe.");
        }

        return this.repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        CreditCard creditCard = this.findById(id);
        creditCard.setIsActive(false);
    }

    private String generateCardNumber() {
        return UUID.randomUUID().toString();
    }

    private String generatePin() {
        Random r = new Random();
        Integer pin = r.nextInt(9000) + 1000;
        return pin.toString();
    }

    private String generateCardValidationCode() {
        Random r = new Random();
        Integer pin = r.nextInt(900) + 100;
        return pin.toString();
    }

    private String generateExpirationDate() {
        LocalDate date = LocalDate.now();
        String month = ("0" + date.getMonthValue()).length() == 2 ? ("0" + date.getMonthValue())
                : String.valueOf(date.getMonthValue());
        int year = date.getYear() + 5;
        String expirationDate = month + "/" + String.valueOf(year).substring(2, 4);

        return expirationDate;
    }
}
