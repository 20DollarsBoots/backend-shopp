package br.com.brn.shopp.bean;

import br.com.brn.shopp.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class UserBean extends AbstractBean<User> {

    @Override
    public Class<User> getClasse() {
        return User.class;
    }


    public List<User> searchAll()throws Exception {
        String sql = "Select u From User u ";
        Query query = entity.createQuery(sql);
        return query.getResultList();
    }

    public User loginValidation(User userLogin) {
        String sql = "Select u From User u where u.email = :email and u.password = :password";
        Query query = entity.createQuery(sql);
        query.setParameter("email", userLogin.getEmail());
        query.setParameter("password", userLogin.getPassword());
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public User searchByName(User user)throws Exception {
        String sql = "Select u From User u where u.name = :name";
        Query query = entity.createQuery(sql);
        query.setParameter("name", user.getName());
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public User singIn(String email, String password)throws Exception {
        String sql = "Select u From User u where upper(u.email) = :email and u.password = :password";
        Query query = entity.createQuery(sql);
        query.setParameter("email", email.trim().toUpperCase());
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        if (user.getToken() == null) {
            user.setToken(generateToken(user));
            user = save(user);
        }
        return user;
    }

    @Override
    public User save(User obj) throws Exception {
        if (obj.getToken() == null) {
            obj.setToken(generateToken(obj));
        }
        return  super.save(obj);
    }

    public String generateToken(User user) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(user.getEmail());
        String token = JWT.create().withIssuer(user.getEmail()).withKeyId(user.getPassword())
                .withClaim("name", user.getName()).sign(algorithm);
        return token;
    }

    public User getToken(String token) throws Exception {
        String sql = "Select u From User u where u.token = :token";
        Query query = entity.createQuery(sql);
        query.setParameter("token", token);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }


}
