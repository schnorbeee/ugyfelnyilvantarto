package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.beans.RoleBean;
import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.daos.ConnectionChannelDaoImpl;
import com.codingmentorteam3.daos.RoleDaoImpl;
import com.codingmentorteam3.daos.UserDaoImpl;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.ConnectionChannelType;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.util.UtilBean;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "userController")
public class UserController extends PageableEntityController<User> {

    @Inject
    private UserDaoImpl userDao;

    @Inject
    private UtilBean utilBean;

    @Inject
    private RoleDaoImpl roleDao;

    @Inject
    private ConnectionChannelDaoImpl connectionChannelDao;

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    public String registrate(UserBean regUser, ConnectionChannelBean regChannal) throws NoSuchAlgorithmException {
        LOG.info("UserController.registrate()");
        User newUser = new User(regUser);
        for(User user : userDao.getUsersList()) {
            if(newUser.equals(user)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build().toString();
            }
        }
        ConnectionChannel newConnectionChannel = new ConnectionChannel(ConnectionChannelType.EMAIL, regChannal.getValue(), newUser);
        for(ConnectionChannel connectionChannel : connectionChannelDao.getConnectionChannelsList()) {
            if(newConnectionChannel.equals(connectionChannel)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build().toString();
            }
        }
        RoleBean newRoleBean = new RoleBean(newUser);
        Role newRole = new Role(newRoleBean);
        for(Role role : roleDao.getRolesList()) {
            if(newRole.equals(role)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build().toString();
            }
        }
        newUser.setPassword(utilBean.sha256coding(newUser.getPassword()));
        userDao.create(newUser);
        connectionChannelDao.create(newConnectionChannel);
        roleDao.create(newRole);
        return "SUCCESS";
    }
    
    public void update(UserBean user) {
        LOG.info("UserController.update()");
        // TODO
    }
    
    public void changePassword(String oldPassword, UserBean user) {
        LOG.info("UserController.changePassword()");
        // TODO
    }

    @Override
    public List<User> getEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected User loadEntity(Long entityId) {
        return new User();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected User doUpdateEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPersistEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getNoEntityMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNewItemOutcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
