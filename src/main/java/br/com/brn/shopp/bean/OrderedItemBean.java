package br.com.brn.shopp.bean;

import br.com.brn.shopp.model.Order;
import br.com.brn.shopp.model.OrderedItem;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class OrderedItemBean extends AbstractBean<OrderedItem> {
    @Override
    public Class<OrderedItem> getClasse() {
        return OrderedItem.class;
    }

    public List<Order> searchAll()throws Exception {
        String sql = "Select o From OrderedItem o ";
        Query query = entity.createQuery(sql);
        return query.getResultList();
    }
}
