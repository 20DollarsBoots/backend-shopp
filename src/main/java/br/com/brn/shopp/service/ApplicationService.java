package br.com.brn.shopp.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class ApplicationService extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> sets = new HashSet<Class<?>>();
         sets.add(FilterRegistration .class);
         sets.add(FilterOrigin.class);
        sets.add(UserService.class);
        sets.add(ProductService.class);
        sets.add(CategoryService.class);
        sets.add(OrderedItemsService.class);
        sets.add(OrderService.class);
        return sets;
    }
}
