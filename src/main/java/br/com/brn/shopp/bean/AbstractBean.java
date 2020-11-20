package br.com.brn.shopp.bean;


import br.com.brn.shopp.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractBean<T extends AbstractEntity> {

    @PersistenceContext(unitName = "shopp")
    protected EntityManager entity;

    public abstract Class<T> getClasse();

    public T insert(T obj)throws Exception {
        entity.persist(obj);
        entity.flush();
        return obj;
    }

    public T change(T obj) throws Exception {
        T old = entity.find(getClasse(), obj.getId());
        obj = entity.merge(obj);
        entity.flush();
        return obj;
    }

    public T save(T obj) throws Exception {
        if (obj.getId() == null) {
            return insert(obj);
        }else {
            return change(obj);
        }
    }

    public void delete(Long id)throws Exception {
        T old = entity.find(getClasse(), id);
        entity.remove(old);
        entity.flush();
    }

    public T searchById(Long id)throws Exception {
        return entity.find(getClasse(), id);
    }
}
