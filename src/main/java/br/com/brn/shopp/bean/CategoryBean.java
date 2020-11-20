package br.com.brn.shopp.bean;

import br.com.brn.shopp.model.Category;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class CategoryBean extends AbstractBean<Category> {
    @Override
    public Class<Category> getClasse() {
        return Category.class;
    }

    public List<Category> searchAll()throws Exception {
        String sql = "Select c From Category c ";
        Query query = entity.createQuery(sql);
        return query.getResultList();
    }

    public Category searchByName(Category category)throws Exception {
        String sql = "Select c From Category c where c.name = :name";
        Query query = entity.createQuery(sql);
        query.setParameter("name", category.getName());
        List<Category> categories = query.getResultList();
        if (categories.isEmpty()) {
            return null;
        }
        return categories.get(0);
    }
}
