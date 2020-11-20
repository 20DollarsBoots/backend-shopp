package br.com.brn.shopp.service;


import br.com.brn.shopp.bean.AbstractBean;
import br.com.brn.shopp.bean.OrderedItemBean;
import br.com.brn.shopp.model.Order;
import br.com.brn.shopp.model.OrderedItem;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("ordered-items")
public class OrderedItemsService extends AbstractService<OrderedItem>{

    @EJB
    private OrderedItemBean orderedItemBeanBean;

    @Override
    public AbstractBean<OrderedItem> getBean() {
        return orderedItemBeanBean;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAll() {
        try {
            List<Order> list = orderedItemBeanBean.searchAll();
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }
}
