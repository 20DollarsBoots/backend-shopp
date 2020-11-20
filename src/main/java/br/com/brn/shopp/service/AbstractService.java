package br.com.brn.shopp.service;

import br.com.brn.shopp.bean.AbstractBean;
import br.com.brn.shopp.model.AbstractEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class AbstractService<T extends AbstractEntity> {

    public abstract AbstractBean<T> getBean();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(T obj) {
        try {
            obj = getBean().insert(obj);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response change(T obj) {
        try {
            obj = getBean().change(obj);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(T obj) {
        try {
            obj = getBean().save(obj);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchById(@PathParam("id")Long id) {
        try {
            T obj = getBean().searchById(id);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id")Long id) {
        try {
            getBean().delete(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

}
