package br.com.brn.shopp.service;

import javax.annotation.Priority;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Priority(1)
@Provider
public class FilterRegistration implements DynamicFeature {
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext featureContext) {
        Method method = resourceInfo.getResourceMethod();
        if (method.getDeclaredAnnotation(IgnoreTokenAuthenticated.class) != null) {
            System.out.println("Ignore FilterAuthentication: " + method.getName() +
                    "in " + resourceInfo.getResourceClass().getName());
            return;
        }
        if (method.getDeclaredAnnotation(IgnoreTokenAuthenticated.class) == null) {
            featureContext.register(new FilterAuthorization());
        } else {
            IgnoreTokenAuthenticated ignore = resourceInfo.getResourceClass().getDeclaredAnnotation(IgnoreTokenAuthenticated.class);
            if (ignore == null) {
                featureContext.register(new FilterAuthorization());
            }
        }
    }
}
