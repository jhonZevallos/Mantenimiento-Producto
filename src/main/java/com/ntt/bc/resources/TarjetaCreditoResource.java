package com.ntt.bc.resources;

import com.ntt.bc.model.TarjetaCredito;
import com.ntt.bc.service.ITarjetaCreditoService;

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

@Path("/tarjetaCredito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarjetaCreditoResource {

    @Inject
    private ITarjetaCreditoService service;

    @GET
    public Response listar() {
        return Response.ok(this.service.listarTodo()).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response buscarXId(@PathParam("id") Long id) {
        TarjetaCredito credito = service.buscarPorId(id);
        if (credito == null) {
            return Response.noContent().build();
        }
        return Response.ok(credito).build();
    }

    @POST
    @Transactional
    public Response guardar(@Valid TarjetaCredito credito) {
        TarjetaCredito nuevoCredito = service.crear(credito);
        return Response.ok(nuevoCredito).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid TarjetaCredito credito) {
        TarjetaCredito actualizarCredito = service.actualizar(credito);
        return Response.ok(actualizarCredito).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        this.service.eliminar(id);
        return Response.status(200).build();
    }
}
