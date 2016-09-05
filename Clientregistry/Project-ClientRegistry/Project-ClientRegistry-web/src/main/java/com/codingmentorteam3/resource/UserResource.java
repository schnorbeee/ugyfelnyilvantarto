package com.codingmentorteam3.resource;

import com.codingmentorteam3.daos.UserDaoImpl;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.util.UtilBean;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@RequestScoped
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserDaoImpl userDao;
    
    @Inject
    private UtilBean utilBean;
    
    @POST
    @Path("registration")
    public Response registration(User regUser) throws NoSuchAlgorithmException {
        User user = userDao.getUserByUsername(regUser.getUsername());
        if(null == user) {
            regUser.setPassword(utilBean.sha256coding(regUser.getPassword()));
            userDao.create(regUser);
            return Response.ok(new UserDTO(regUser)).build();
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }
}
