package br.com.brn.shopp.service;


import br.com.brn.shopp.bean.AbstractBean;
import br.com.brn.shopp.bean.CategoryBean;
import br.com.brn.shopp.model.Category;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryService extends AbstractService<Category>{

    @EJB
    private CategoryBean categoryBean;

    @Override
    public AbstractBean<Category> getBean() {
        return categoryBean;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAll() {
        try {
            List<Category> list = categoryBean.searchAll();
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @Path("/search/name")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(Category category) {
        try {
            Category obj = categoryBean.searchByName(category);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }
}
