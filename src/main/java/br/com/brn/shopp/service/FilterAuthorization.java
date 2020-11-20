package br.com.brn.shopp.service;

import br.com.brn.shopp.bean.UserBean;
import br.com.brn.shopp.model.User;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FilterAuthorization implements ContainerRequestFilter {

    private UserBean userBean;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        List<String> list = request.getHeaders().get("authorization");
        if (list == null) {
            request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Não foi enviado o token").build());
            return;
        }
        if (list.isEmpty()) {
            request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Não foi enviado o token!").build());
        } else {
            String token = list.get(0);
            if (token.startsWith("Bearer")) {
                token = token.substring(7); //Pega apenas o token ignorando a palara Bearer
            }
            try {
                InitialContext context = new InitialContext();
                userBean = (UserBean) context.lookup("java:module/UserBean!br.com.brn.shopp.bean.UserBean");
                User user = userBean.getToken(token);
                if (user == null) {
                    request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Não foi enviado o token!!").build());
                }
            } catch (Exception e) {
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Não foi enviado o token!@").build());
            }
        }
    }
}
