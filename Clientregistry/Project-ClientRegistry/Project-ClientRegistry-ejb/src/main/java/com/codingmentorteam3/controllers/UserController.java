package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.beans.RoleBean;
import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.EventDTO;
import com.codingmentorteam3.dtos.InvitationDTO;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.ConnectionChannelType;
import com.codingmentorteam3.enums.FeedbackType;
import com.codingmentorteam3.enums.NumItemsPerPageType;
import com.codingmentorteam3.enums.PageableTablesType;
import com.codingmentorteam3.enums.RoleType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.exceptions.query.LastAdminException;
import com.codingmentorteam3.exceptions.query.OldPasswordException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ConnectionChannelService;
import com.codingmentorteam3.services.InvitationService;
import com.codingmentorteam3.services.PersonService;
import com.codingmentorteam3.services.RoleService;
import com.codingmentorteam3.services.UserService;
import com.codingmentorteam3.util.UtilBean;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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
    private PersonService personService;

    @Inject
    private RoleService roleService;

    @Inject
    private ConnectionChannelService connectionChannelService;

    @Inject
    private InvitationService invitationService;

    @Inject
    private UtilBean utilBean;

    //user method
    public String registrate(UserBean regUser, ConnectionChannelBean regChannal) throws NoSuchAlgorithmException {
        User newUser = new User(regUser);
        if (null != userService.getUserByUsername(newUser.getUsername())) {
            throw new EntityAlreadyExistsException("This user already exists in our database.");
        }
        ConnectionChannelBean newConnectionChannelBean = new ConnectionChannelBean(ConnectionChannelType.EMAIL, regChannal.getValue(), newUser);
        ConnectionChannel newConnectionChannal = new ConnectionChannel(newConnectionChannelBean);
        RoleBean newRoleBean = new RoleBean(newUser);
        Role newRole = new Role(newRoleBean);
        newUser.setPassword(utilBean.sha256coding(newUser.getPassword()));
        newUser.setNumItemPerPage(preSetTableLimitsToNewUser());
        setEntity(newUser);
        saveEntity();
        connectionChannelService.createConnectionChannel(newConnectionChannal);
        roleService.createRole(newRole);
        return getListPage();
    }

    public String createNewConnectionChannel(ConnectionChannelBean newConnectionChannel) {
        ConnectionChannel regConnectionChannel = new ConnectionChannel(newConnectionChannel);
        for (ConnectionChannel cc : personService.getConnectionChannelsListByPersonId(getEntityId())) {
            ConnectionChannelBean connectionChannelBean = new ConnectionChannelBean(cc.getType(), cc.getValue(), cc.getOwner());
            if (newConnectionChannel.equals(connectionChannelBean)) {
                throw new EntityAlreadyExistsException("This connection channel already exists in our database.");
            }
        }
        regConnectionChannel.setOwner(userService.getUser(getEntityId()));
        connectionChannelService.createConnectionChannel(regConnectionChannel);
        return "profile.xhtml";
    }

//    //user method
//    public String getUserById(Long userId) {
//        User user = userService.getUser(userId);
//        if (null != user) {
//            return "";
//        }
//        throw new BadRequestException(getNoEntityMessage());
//    }
    //user method
    public UserDTO updateUserPersonalInfos(UserBean updateUser, Long userId) throws NoSuchAlgorithmException {
        User oldUser = loadEntity(userId);
        if (null != oldUser) {
            User currentUser = new User(updateUser);
            setEntity(modifiedCheckerUser(oldUser, currentUser));
            saveEntity();
            return new UserDTO(oldUser);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public UserDTO updateUserPassword(UserBean updateUser, String oldPassword, Long userId) throws NoSuchAlgorithmException {
        User oldUser = loadEntity(userId);
        if (null != oldUser) {
            if (utilBean.sha256coding(oldPassword).equals(oldUser.getPassword())) {
                oldUser.setPassword(utilBean.sha256coding(oldPassword));
                setEntity(oldUser);
                saveEntity();
                return new UserDTO(oldUser);
            }
            throw new OldPasswordException("Old password doesn't equals what you wrote.");
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public UserDTO changeAvatar(String newAvatar, Long userId) {
        User currentUser = loadEntity(userId);
        if (null != currentUser) {
            currentUser.setAvatar(newAvatar);
            setEntity(currentUser);
            saveEntity();
            return new UserDTO(currentUser);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //admin method
    public List<UserDTO> deleteUserById(Long userId) {
        User deleteUser = loadEntity(userId);
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

    public List<UserDTO> getUserListByRoleType(RoleType type) {
        if (null != type) {
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : roleService.getUsersListByRoleType(type, getLimit(), getOffset())) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException("This role type is not definied in database.");
    }

    public List<InvitationDTO> getInvitationsListByReceiverIdAndAcceptedFeedback(Long userId) {
        User currentReceiver = loadEntity(userId);
        if (null != currentReceiver) {
            FeedbackType type = FeedbackType.ACCEPTED;
            List<InvitationDTO> invitationDTOs = new ArrayList<>();
            for (Invitation i : invitationService.getInvitationsListByReceiverIdAndFeedbackStatus(getEntityId(), type, getLimit(), getOffset())) {
                InvitationDTO invitationDTO = new InvitationDTO(i);
                invitationDTOs.add(invitationDTO);
            }
            return invitationDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<InvitationDTO> getInvitationsListBySenderId(Long userId) {
        User currentSender = loadEntity(userId);
        if (null != currentSender) {
            List<InvitationDTO> invitationDTOs = new ArrayList<>();
            for (Invitation i : invitationService.getInvitationsListBySenderId(getEntityId(), getLimit(), getOffset())) {
                InvitationDTO invitationDTO = new InvitationDTO(i);
                invitationDTOs.add(invitationDTO);
            }
            return invitationDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //ADMIN METHOD
    public List<UserDTO> setUserAdminRoleToRoleTable(Long userId) {
        User currentUser = loadEntity(userId);
        RoleType adminRole = RoleType.ADMIN;
        if (null != currentUser) {
            for (Role role : userService.getRolesListByUserId(getEntityId())) {
                if (adminRole.equals(role.getRoleType())) {
                    throw new EntityAlreadyExistsException("This user already have admin role.");
                }
            }
            RoleBean newRoleBean = new RoleBean(currentUser);
            newRoleBean.setType(adminRole);
            Role newRole = new Role(newRoleBean);
            roleService.createRole(newRole);
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : roleService.getUsersListByRoleType(adminRole, getLimit(), getOffset())) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<UserDTO> setUserManagerRoleToRoleTable(Long userId) {
        User currentUser = loadEntity(userId);
        RoleType managerRole = RoleType.MANAGER;
        if (null != currentUser) {
            for (Role role : userService.getRolesListByUserId(getEntityId())) {
                if (managerRole.equals(role.getRoleType())) {
                    throw new EntityAlreadyExistsException("This user already have manager role.");
                }
            }
            RoleBean newRoleBean = new RoleBean(currentUser);
            newRoleBean.setType(managerRole);
            Role newRole = new Role(newRoleBean);
            roleService.createRole(newRole);
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : roleService.getUsersListByRoleType(managerRole, getLimit(), getOffset())) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //ADMIN METHOD
    public List<UserDTO> deleteAdminRoleForThisUser(Long userId) {
        User current = loadEntity(userId);
        RoleType adminRole = RoleType.ADMIN;
        if (null != current) {
            if (roleService.getUsersListByRoleType(adminRole, getLimit(), getOffset()).size() == 1) {
                throw new LastAdminException("This admin is last admin in database. So you can't delete his role.");
            }
            for (Role r : roleService.getRolesListByUsername(current.getUsername())) {
                if (r.getRoleType().equals(adminRole)) {
                    roleService.deleteRole(r);
                }
            }
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : roleService.getUsersListByRoleType(adminRole, getLimit(), getOffset())) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //ADMIN METHOD
    public List<UserDTO> deleteManagerRoleForThisUser(Long userId) {
        User current = loadEntity(userId);
        RoleType managerRole = RoleType.MANAGER;
        if (null != current) {
            for (Role r : roleService.getRolesListByUsername(current.getUsername())) {
                if (r.getRoleType().equals(managerRole)) {
                    roleService.deleteRole(r);
                }
            }
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : roleService.getUsersListByRoleType(managerRole, getLimit(), getOffset())) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<EventDTO> getEventsListByUserId(Long userId) {
        User currentUser = loadEntity(userId);
        if (null != currentUser) {
            List<EventDTO> eventDTOs = new ArrayList<>();
            for (Event e : userService.getEventsListByUserId(getEntityId(), getLimit(), getOffset())) {
                EventDTO eventDTO = new EventDTO(e);
                eventDTOs.add(eventDTO);
            }
            return eventDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<UserDTO> getUsersListByNameFilter(String name) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User u : userService.getUsersListByNameFilter(name, getLimit(), getOffset())) {
            UserDTO userDTO = new UserDTO(u);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public List<UserDTO> getUsersListByPositionFilter(String position) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User u : userService.getUsersListByPositionFilter(position, getLimit(), getOffset())) {
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
        return null;
    }

    //atnezni a stringek helyesek-e az alabbi 3 override-nal
    @Override
    public String getListPage() {
        return "login";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editProfile.xhtml";
    }

    @Override
    public String getNoEntityMessage() {
        return "No user found in database!";
    }

    private User modifiedCheckerUser(User oldUser, User currentUser) {
        if (!currentUser.getUsername().equals("") || !currentUser.getUsername().equals(oldUser.getUsername())) {
            oldUser.setUsername(currentUser.getUsername());
        }
        if (!currentUser.getFirstName().equals("") || !currentUser.getFirstName().equals(oldUser.getFirstName())) {
            oldUser.setFirstName(currentUser.getFirstName());
        }
        if (!currentUser.getLastName().equals("") || !currentUser.getLastName().equals(oldUser.getLastName())) {
            oldUser.setLastName(currentUser.getLastName());
        }
        return oldUser;
    }

    private Map<PageableTablesType, NumItemsPerPageType> preSetTableLimitsToNewUser() {
        NumItemsPerPageType tenItemPerPage = NumItemsPerPageType.TEN;
        Map<PageableTablesType, NumItemsPerPageType> numItemPerPage = new EnumMap<>(PageableTablesType.class);
        numItemPerPage.put(PageableTablesType.FULL_USER_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.FULL_EVENT_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.FULL_COMPANY_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.FULL_PROJECT_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.MANAGED_USER_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.MANAGED_EVENT_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.MANAGED_COMPANY_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.MANAGED_PROJECT_TABLE, tenItemPerPage);
        numItemPerPage.put(PageableTablesType.INVITATION_TABLE, tenItemPerPage);
        return numItemPerPage;
    }

}
