package br.com.zup.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.zup.controller.facade.FacadeLocal;
import br.com.zup.model.entity.PontoInteresse;

/**
 * 
 */
@Stateless
@Path("/pontointeresses")
public class PontoInteresseResource {

    @EJB
    private FacadeLocal facade;

    @POST
    @Consumes("application/json")
    public Response create(PontoInteresse entity) {
	try { 
	    facade.salvar(entity);
	} catch (Exception e) {
	    return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
	}

	return Response.created(
		UriBuilder.fromResource(PontoInteresseResource.class).path(String.valueOf(entity.getId())).build())
		.build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
	try {
	    facade.deletePontoInteresse(id);
	} catch (NoResultException e) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Response findById(@PathParam("id") Long id) {

	try {
	    final PontoInteresse entity = facade.getPontoInteresseById(id);
	    return Response.ok(entity).build();

	} catch (NoResultException nre) {
	    return Response.status(Status.NOT_FOUND).build();
	}
    }

    @GET
    @Produces("application/json")
    public List<PontoInteresse> listAll(@QueryParam("start") Integer startPosition,
	    @QueryParam("max") Integer maxResult, 
	    @QueryParam("x") Integer coordX, @QueryParam("y") Integer coordY, @QueryParam("d-max") Integer dMax) {

	final List<PontoInteresse> results = facade.listarPontos(startPosition, maxResult, coordX, coordY, dMax);
	return results;
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes("application/json")
    public Response update(@PathParam("id") Long id, PontoInteresse entity) {
	if (entity == null) {
	    return Response.status(Status.BAD_REQUEST).build();
	}
	if (id == null) {
	    return Response.status(Status.BAD_REQUEST).build();
	}
	if (!id.equals(entity.getId())) {
	    return Response.status(Status.CONFLICT).entity(entity).build();
	}
	try {

	    facade.salvar(entity);

	} catch (NoResultException e) {

	    return Response.status(Status.NOT_FOUND).build();

	} catch (OptimisticLockException e) {
	    return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
	} catch (Exception e) {
	    return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
	}

	return Response.noContent().build();
    }
}
