package br.com.brn.shopp.bean;

import br.com.brn.shopp.model.Product;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class ProductBean extends AbstractBean<Product> {
    @Override
    public Class<Product> getClasse() {
        return Product.class;
    }

    public List<Product> searchAll()throws Exception {
        String sql = "Select p From Product p ";
        Query query = entity.createQuery(sql);
        return query.getResultList();
    }

    public Product searchByTitle(Product product)throws Exception {
        String sql = "Select c From Product c where c.title = :title";
        Query query = entity.createQuery(sql);
        query.setParameter("title", product.getTitle());
        List<Product> products = query.getResultList();
        if (products.isEmpty()) {
            return null;
        }
        return products.get(0);
    }
}
