package com.ntt.bc.service.impl;

import java.util.List;

import com.ntt.bc.model.Credito;
import com.ntt.bc.repository.CreditoRepository;
import com.ntt.bc.service.ICreditoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CreditoServiceImpl implements ICreditoService {

    @Inject
    private CreditoRepository repository;

    @Override
    public Credito crear(Credito obj) {
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public Credito actualizar(Credito obj) {
        Credito credito = this.repository.findById(obj.getIdCredito());
        if (credito == null) {
            throw new NotFoundException("El credito no existe");
        }
        credito.setCuotas(obj.getCuotas());
        credito.setFechaInicio(obj.getFechaInicio());
        credito.setFechaPago(obj.getFechaPago());
        credito.setSaldoActual(obj.getSaldoActual());
        credito.setSaldoInicial(obj.getSaldoInicial());
        return credito;
    }

    @Override
    public List<Credito> listarTodo() {

        return this.repository.listAll();
    }

    @Override
    public Credito buscarPorId(Long id) {

        return this.repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        this.repository.deleteById(id);

    }

}
