package com.codingmentorteam3.resource;

import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@SessionScoped
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource implements Serializable {

    @POST
    @Path("login")
    public Response login(@Context SecurityContext ctx,
            @Context HttpServletRequest request, User user) throws ServletException {
        request.getSession(true);
        request.login(user.getUsername(), user.getPassword());
        return Response.ok().build();
    }
    
    @POST
    @Path("logout")
    public Response logout(@Context SecurityContext ctx, @Context HttpServletRequest request,
            UserDTO dto) throws ServletException {
        request.getSession(true);
        request.logout();
        return Response.ok().build();
    }
    
}
