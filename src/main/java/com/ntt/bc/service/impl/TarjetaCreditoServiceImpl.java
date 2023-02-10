package com.ntt.bc.service.impl;

import java.util.List;

import com.ntt.bc.model.TarjetaCredito;
import com.ntt.bc.repository.TarjetaCreditoRepository;
import com.ntt.bc.service.ITarjetaCreditoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TarjetaCreditoServiceImpl implements ITarjetaCreditoService {

    @Inject
    private TarjetaCreditoRepository repository;

    @Override
    public TarjetaCredito crear(TarjetaCredito obj) {
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public TarjetaCredito actualizar(TarjetaCredito obj) {
        TarjetaCredito credito = this.repository.findById(obj.getIdTarjeta());
        if (credito == null) {
            throw new NotFoundException("La tarjeta no existe");
        }
        credito.setNumTarjeta(obj.getNumTarjeta());
        credito.setFechaVencimiento(obj.getFechaVencimiento());
        credito.setCodValidacion(obj.getCodValidacion());
        credito.setPin(obj.getPin());
        credito.setFechaCorte(obj.getFechaCorte());
        credito.setFechaLimitePago(obj.getFechaLimitePago());
        credito.setSaldo(obj.getSaldo());
        credito.setCredito(obj.getCredito());
        return credito;
    }

    @Override
    public List<TarjetaCredito> listarTodo() {

        return this.repository.listAll();
    }

    @Override
    public TarjetaCredito buscarPorId(Long id) {

        return this.repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        this.repository.deleteById(id);
    }

}
