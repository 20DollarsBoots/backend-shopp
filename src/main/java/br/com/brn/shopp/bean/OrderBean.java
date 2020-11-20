package br.com.brn.shopp.bean;

import br.com.brn.shopp.model.Order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class OrderBean extends AbstractBean<Order> {
    @Override
    public Class<Order> getClasse() {
        return Order.class;
    }

    public List<Order> searchAll()throws Exception {
        String sql = "Select o From Order o ";
        Query query = entity.createQuery(sql);
        return query.getResultList();
    }
}
