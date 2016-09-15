package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.util.UtilSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@ManagedBean(name = "authController")
@SessionScoped
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

    public String login(UserBean user) throws ServletException {
        LOG.info("AuthController.login()");
        HttpSession session = UtilSession.getSession();
        session.setAttribute("user", user);
        return Response.ok().build().toString();
    }

    public void logout() {
        LOG.info("AuthController.logout()");
        // TODO
    }

}
