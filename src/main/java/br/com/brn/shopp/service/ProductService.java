package br.com.brn.shopp.service;


import br.com.brn.shopp.bean.AbstractBean;
import br.com.brn.shopp.bean.ProductBean;
import br.com.brn.shopp.model.Product;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("products")
public class ProductService extends AbstractService<Product>{

    @EJB
    private ProductBean productBean;

    @Override
    public AbstractBean<Product> getBean() {
        return productBean;
    }

    @GET
    @IgnoreTokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAll() {
        try {
            List<Product> list = productBean.searchAll();
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @Path("search/name")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(Product product) {
        try {
            Product obj = productBean.searchByTitle(product);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }
}
