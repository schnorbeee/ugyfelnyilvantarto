package com.codingmentorteam3.dao;

import com.codingmentorteam3.entities.User;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class UserDaoImpl extends AbstractDao<User> {

    public UserDaoImpl() {
        super(User.class);
    }

    public User findUserByUsername(String username) {
        try {
            User query = em.createNamedQuery("Get user by username", User.class)
                .setParameter("username", username)
                .getSingleResult();
            return query;
        } catch (Exception ex) {
            return null;
        }
    }
    
}
