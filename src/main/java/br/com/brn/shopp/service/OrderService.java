package br.com.brn.shopp.service;


import br.com.brn.shopp.bean.AbstractBean;
import br.com.brn.shopp.bean.OrderBean;
import br.com.brn.shopp.model.Order;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("orders")
public class OrderService extends AbstractService<Order>{

    @EJB
    private OrderBean orderBean;

    @Override
    public AbstractBean<Order> getBean() {
        return orderBean;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAll() {
        try {
            List<Order> list = orderBean.searchAll();
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }
}
