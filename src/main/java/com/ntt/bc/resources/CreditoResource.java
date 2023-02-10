package com.ntt.bc.resources;

import com.ntt.bc.model.Credito;
import com.ntt.bc.service.ICreditoService;

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

@Path("/credito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreditoResource {

    @Inject
    private ICreditoService service;

    @GET
    public Response listar() {
        return Response.ok(this.service.listarTodo()).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response buscarXId(@PathParam("id") Long id) {
        Credito credito = service.buscarPorId(id);
        if (credito == null) {
            return Response.noContent().build();
        }
        return Response.ok(credito).build();
    }

    @POST
    @Transactional
    public Response guardar(@Valid Credito credito) {
        Credito nuevCredito = service.crear(credito);
        return Response.ok(nuevCredito).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid Credito credito) {
        Credito actualizar = service.actualizar(credito);
        return Response.ok(actualizar).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        this.service.eliminar(id);
        return Response.status(200).build();
    }

}
