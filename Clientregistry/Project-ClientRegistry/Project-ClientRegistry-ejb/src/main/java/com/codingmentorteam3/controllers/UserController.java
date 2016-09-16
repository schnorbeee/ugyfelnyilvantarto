package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.beans.RoleBean;
import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.dtos.RoleDTO;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.ConnectionChannelType;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ConnectionChannelService;
import com.codingmentorteam3.services.RoleService;
import com.codingmentorteam3.services.UserService;
import com.codingmentorteam3.util.UtilBean;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "userController")
public class UserController extends PageableEntityController<User> {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private ConnectionChannelService connectionChannelService;

    @Inject
    private UtilBean utilBean;

    //user method
    public Response registrate(UserBean regUser, ConnectionChannelBean regChannal) throws NoSuchAlgorithmException {
        User newUser = new User(regUser);
        if (null != userService.getUserByUsername(newUser.getUsername())) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        ConnectionChannelBean newConnectionChannelBean = new ConnectionChannelBean(ConnectionChannelType.EMAIL, regChannal.getValue(), newUser);
        ConnectionChannel newConnectionChannal = new ConnectionChannel(newConnectionChannelBean);
        RoleBean newRoleBean = new RoleBean(newUser);
        Role newRole = new Role(newRoleBean);
        newUser.setPassword(utilBean.sha256coding(newUser.getPassword()));
        userService.createUser(newUser);
        connectionChannelService.createConnectionChannel(newConnectionChannal);
        roleService.createRole(newRole);
        UserDTO userDto = new UserDTO(newUser);
        ConnectionChannelDTO connectionChannelDto = new ConnectionChannelDTO(newConnectionChannal);
        RoleDTO roleDto = new RoleDTO(newRole);
        return Response.status(Response.Status.CREATED).entity(userDto).entity(connectionChannelDto).entity(roleDto).type(MediaType.APPLICATION_JSON).build();
    }

    //user method
    public Response getUserById(@QueryParam("user_id") Long id) {
        User user = userService.getUser(id);
        if (null != user) {
            UserDTO dto = new UserDTO(user);
            return Response.status(Response.Status.FOUND).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //user method
    public Response updateUserPersonalInfos(UserBean updateUser, @QueryParam("user_id") Long id) throws NoSuchAlgorithmException {
        if (null != updateUser) {
            User currentUser = new User(updateUser);
            User oldUser = userService.getUser(id);
            if (null != oldUser) {
                userService.editUser(modifiedChecker(oldUser, currentUser));
                UserDTO dto = new UserDTO(oldUser);
                return Response.status(Response.Status.ACCEPTED).entity(dto).type(MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    //user method
    public Response updateUserPassword(UserBean updateUser, String oldPassword, @QueryParam("user_id") Long id) throws NoSuchAlgorithmException {
        User oldUser = userService.getUser(id);
        if (null != oldUser && null != oldPassword) {
            if (oldUser.getPassword().equals(utilBean.sha256coding(oldPassword))) {
                if(null != updateUser) {
                    oldUser.setPassword(utilBean.sha256coding(oldPassword));
                    userService.editUser(oldUser);
                    UserDTO dto = new UserDTO(oldUser);
                    return Response.status(Response.Status.ACCEPTED).entity(dto).type(MediaType.APPLICATION_JSON).build();
                }
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    //admin method
    public Response deleteUserById(@QueryParam("user_id") Long id) {
        User deleteUser = userService.getUser(id);
        if (null != deleteUser) {
            userService.deleteUser(deleteUser);
            UserDTO dto = new UserDTO(deleteUser);
            return Response.status(Response.Status.ACCEPTED).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getUsersList() {
        List<UserDTO> users = new ArrayList();
        for (User u : getEntities()) {
            UserDTO user = new UserDTO(u);
            users.add(user);
        }
        return Response.ok(users).type(MediaType.APPLICATION_JSON).build();
    }
    
    @Override
    protected void doPersistEntity(){
        userService.createUser(getEntity());
    }
    
    @Override
    protected User doUpdateEntity(){
        setEntity(userService.editUser(getEntity()));
        return getEntity();
    }
    
    @Override
    public List<User> getEntities() {
        return userService.getUsersList(getLimit(), getOffset());
    }

    @Override
    protected User loadEntity(Long entityId) {
        if (entityId != null) {
            return userService.getUser(entityId);
        }
        return new User();
    }

    //atnezni a stringek helyesek-e az alabbi 3 override-nal
    @Override
    public String getListPage() {
        return "user-list";
    }

    @Override
    public String getNewItemOutcome() {
        return "composite/user.xhtml";
    }

    @Override
    public String getNoEntityMessage() {
        return "No user found in database!";
    }

    public User modifiedChecker(User oldUser, User currentUser) {
        if (!currentUser.getUsername().equals("")) {
            oldUser.setUsername(currentUser.getUsername());
        }
        if (!currentUser.getFirstName().equals("")) {
            oldUser.setFirstName(currentUser.getFirstName());
        }
        if (!currentUser.getLastName().equals("")) {
            oldUser.setLastName(currentUser.getLastName());
        }
        return oldUser;
    }

}
