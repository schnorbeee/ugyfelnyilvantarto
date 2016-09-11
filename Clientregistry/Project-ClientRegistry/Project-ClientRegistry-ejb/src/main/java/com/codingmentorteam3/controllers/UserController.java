package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.daos.UserDaoImpl;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.util.UtilBean;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@SessionScoped
@ManagedBean(name = "userController")
public class UserController implements Serializable {
    
    @Inject
    private UserDaoImpl userDao;
    
    @Inject
    private UtilBean utilBean;

    public Response login(@Context SecurityContext ctx,
            @Context HttpServletRequest request, User user) throws ServletException {
        request.getSession(true);
        request.login(user.getUsername(), user.getPassword());
        return Response.ok().build();
    }
    
    public Response logout(@Context SecurityContext ctx, @Context HttpServletRequest request,
            UserDTO dto) throws ServletException {
        request.getSession(true);
        request.logout();
        return Response.ok().build();
    }
    
    public Response registration(UserBean regUser) throws NoSuchAlgorithmException {
        User newUser = new User(regUser);
        User user = userDao.getUserByUsername(newUser.getUsername());
        if(null == user) {
            newUser.setPassword(utilBean.sha256coding(newUser.getPassword()));
            userDao.create(newUser);
            return Response.ok(new UserDTO(newUser)).build();
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }
    
    public Response registration(User regUser) throws NoSuchAlgorithmException {
        try {
            userDao.getUserByUsername(regUser.getUsername());
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            regUser.setPassword(utilBean.sha256coding(regUser.getPassword()));
            userDao.create(regUser);
            return Response.ok(new UserDTO(regUser)).build();
        }
    }
}
