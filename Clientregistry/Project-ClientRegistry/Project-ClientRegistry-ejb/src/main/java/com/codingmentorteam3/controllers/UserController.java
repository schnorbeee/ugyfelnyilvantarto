package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.beans.RoleBean;
import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.ConnectionChannelType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.exceptions.query.OldPasswordException;
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
    public String registrate(UserBean regUser, ConnectionChannelBean regChannal) throws NoSuchAlgorithmException {
        User newUser = new User(regUser);
        if (null != userService.getUserByUsername(newUser.getUsername())) {
            throw new EntityAlreadyExistsException("This user is already exist in our database.");
        }
        ConnectionChannelBean newConnectionChannelBean = new ConnectionChannelBean(ConnectionChannelType.EMAIL, regChannal.getValue(), newUser);
        ConnectionChannel newConnectionChannal = new ConnectionChannel(newConnectionChannelBean);
        RoleBean newRoleBean = new RoleBean(newUser);
        Role newRole = new Role(newRoleBean);
        newUser.setPassword(utilBean.sha256coding(newUser.getPassword()));
        userService.createUser(newUser);
        connectionChannelService.createConnectionChannel(newConnectionChannal);
        roleService.createRole(newRole);
        return "";
    }

    //user method
    public String getUserById(Long userId) {
        User user = userService.getUser(userId);
        if (null != user) {
            return "";
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public UserDTO updateUserPersonalInfos(UserBean updateUser, Long id) throws NoSuchAlgorithmException {
        User oldUser = userService.getUser(id);
        if (null != oldUser) {
            User currentUser = new User(updateUser);
            userService.editUser(modifiedChecker(oldUser, currentUser));
            return new UserDTO(oldUser);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public UserDTO updateUserPassword(UserBean updateUser, String oldPassword, Long id) throws NoSuchAlgorithmException {
        User oldUser = userService.getUser(id);
        if (null != oldUser) {
            if (utilBean.sha256coding(oldPassword).equals(oldUser.getPassword())) {
                oldUser.setPassword(utilBean.sha256coding(oldPassword));
                userService.editUser(oldUser);
                return new UserDTO(oldUser);
            }
            throw new OldPasswordException("Old password doesn't equals what you wrote.");
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public UserDTO changeAvatar(String newAvatar, Long id) {
        User currentUser = userService.getUser(id);
        if (null != currentUser) {
            currentUser.setAvatar(newAvatar);
            userService.editUser(currentUser);
            return new UserDTO(currentUser);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //admin method
    public List<UserDTO> deleteUserById(Long id) {
        User deleteUser = userService.getUser(id);
        if (null != deleteUser) {
            userService.deleteUser(deleteUser);
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : getEntities()) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public List<UserDTO> getUsersList() {
        List<UserDTO> userDTOs = new ArrayList();
        for (User u : getEntities()) {
            UserDTO userDTO = new UserDTO(u);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    protected void doPersistEntity() {
        userService.createUser(getEntity());
    }

    @Override
    protected User doUpdateEntity() {
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
        return "users";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editProfile.xhtml";
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
