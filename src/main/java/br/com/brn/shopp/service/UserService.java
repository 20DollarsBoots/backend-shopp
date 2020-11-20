package br.com.brn.shopp.service;

import br.com.brn.shopp.bean.AbstractBean;
import br.com.brn.shopp.bean.UserBean;
import br.com.brn.shopp.model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UserService extends AbstractService<User> {

    @EJB
    private UserBean userBean;

    @Override
    public AbstractBean<User> getBean() {
        return userBean;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAll() {
        try {
            List<User> list = userBean.searchAll();
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

//    @Path("login")
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response login(User user) {
//        try {
//            User obj = userBean.loginValidation(user);
//            return Response.ok(obj).build();
//        } catch (Exception e) {
//            return Response.serverError().entity(e.getLocalizedMessage()).build();
//        }
//    }

    @Path("search/name")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(User user) {
        try {
            User obj = userBean.searchByName(user);
            return Response.ok(obj).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

    @POST
    @Path("signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @IgnoreTokenAuthenticated
    public Response singIn(User user) {
        try {
            user = userBean.singIn(user.getEmail(), user.getPassword());
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }
    }

}
