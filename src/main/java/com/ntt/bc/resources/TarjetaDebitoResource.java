package com.ntt.bc.resources;

import com.ntt.bc.model.TarjetaDebito;
import com.ntt.bc.service.ITarjetaDebitoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/debito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarjetaDebitoResource {

    @Inject
    private ITarjetaDebitoService service;

    @GET
    public Response listar() {
        return Response.ok(this.service.listarTodo()).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response buscarXId(@PathParam("id") Long id) {
        TarjetaDebito debito = service.buscarPorId(id);
        if (debito == null) {
            return Response.noContent().build();
        }
        return Response.ok(debito).build();
    }

    @POST
    @Transactional
    public Response guardar(@Valid TarjetaDebito debito) {
        TarjetaDebito nuevoDebito = service.crear(debito);
        return Response.ok(nuevoDebito).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid TarjetaDebito debito) {
        TarjetaDebito actualizarDebito = service.actualizar(debito);
        return Response.ok(actualizarDebito).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        this.service.eliminar(id);
        return Response.noContent().build();
    }
}
