package com.ntt.bc.service.impl;

import java.util.List;

import com.ntt.bc.model.TarjetaDebito;
import com.ntt.bc.repository.TarjetaDebitoRepository;
import com.ntt.bc.service.ITarjetaDebitoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TarjetaDebitoServiceImpl implements ITarjetaDebitoService {

    @Inject
    private TarjetaDebitoRepository repository;

    @Override
    public TarjetaDebito crear(TarjetaDebito obj) {
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public TarjetaDebito actualizar(TarjetaDebito obj) {
        TarjetaDebito debito = this.repository.findById(obj.getIdTarjeta());
        if (debito == null) {
            throw new NotFoundException("La tarjerta no existe");
        }
        debito.setNumTarjeta(obj.getNumTarjeta());
        debito.setFechaVencimiento(obj.getFechaVencimiento());
        debito.setCodValidacion(obj.getCodValidacion());
        debito.setPin(obj.getPin());
        return debito;
    }

    @Override
    public List<TarjetaDebito> listarTodo() {

        return this.repository.listAll();
    }

    @Override
    public TarjetaDebito buscarPorId(Long id) {

        return this.repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        this.repository.deleteById(id);
    }

}
