package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.UserDaoImpl;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class UserService {

    @Inject
    private UserDaoImpl userDao;

    public void createUser(User user) {
        userDao.create(user);
    }

    public User getUser(Long userId) {
        return userDao.read(userId);
    }

    public User editUser(User user) {
        return userDao.update(user);
    }

    public User deleteUser(User user) {
        return userDao.delete(user);
    }

    public List<User> getUsersList(Integer limit, Integer offset) {
        return userDao.getUsersList(limit, offset);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public List<Role> getRolesListByUserId(Long id) {
        return userDao.getRolesListByUserId(id);
    }

    public List<Event> getEventsListByUserId(Long userId, Integer limit, Integer offset) {
        return userDao.getEventsListByUserId(userId, limit, offset);
    }

    public List<User> getUsersListByNameFilter(String name, Integer limit, Integer offset) {
        return userDao.getUsersListByNameFilter(name, limit, offset);
    }

    public List<User> getUsersListByPositionFilter(String position, Integer limit, Integer offset) {
        return userDao.getUsersListByPositionFilter(position, limit, offset);
    }
    
}
