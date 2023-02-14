package com.nttdata.bc.resources;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.models.CreditCard;
import com.nttdata.bc.services.ICreditCardService;

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

@Path("/credit-cards")
public class CreditCardResource {
    @Inject
    Logger logger;

    @Inject
    private ICreditCardService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(@Valid CreditCard obj) {
        CreditCard creditCard = this.service.insert(obj);
        return Response.status(Status.CREATED).entity(creditCard).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer id, @Valid CreditCard obj) {
        logger.info("Inicio ::: update ::: " + obj);
        obj.setCreditCardId(id);
        CreditCard creditCard = this.service.update(obj);
        return Response.status(Status.CREATED).entity(creditCard).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fintAll() {
        List<CreditCard> products = this.service.findAll();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        CreditCard creditCard = this.service.findById(id);
        return Response.ok(creditCard).build();
    }

    @PUT
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        this.service.delete(id);
        return Response.noContent().build();
    }
}
