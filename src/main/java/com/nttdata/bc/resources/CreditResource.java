package com.nttdata.bc.resources;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.models.Credit;
import com.nttdata.bc.services.ICreditService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/credits")
public class CreditResource {
    @Inject
    Logger logger;

    @Inject
    private ICreditService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(@Valid Credit obj) {
        Credit credit = this.service.insert(obj);
        return Response.status(Status.CREATED).entity(credit).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer id, @Valid Credit obj) {
        logger.info("Inicio ::: update ::: " + obj);
        obj.setCreditId(id);
        Credit credit = this.service.update(obj);
        return Response.status(Status.CREATED).entity(credit).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fintAll() {
        List<Credit> credits = this.service.findAll();
        return Response.ok(credits).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        Credit credit = this.service.findById(id);
        return Response.ok(credit).build();
    }

    @PUT
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        this.service.delete(id);
        return Response.noContent().build();
    }
}
