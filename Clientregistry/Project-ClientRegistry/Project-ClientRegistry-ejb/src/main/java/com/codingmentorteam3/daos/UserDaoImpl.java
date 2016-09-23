package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class UserDaoImpl extends AbstractDao<User> {

    public UserDaoImpl() {
        super(User.class);
    }

    public List<User> getUsersListByNameFilter(String name, int limit, int offset) {
        if (null != name) {
            TypedQuery<User> query = em.createNamedQuery("user.by.name.filter", User.class);
            query.setParameter("name", "%" + name + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<User> query = em.createNamedQuery("user.list", User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    //kerdeses hogy kell-e?
    public List<User> getUsersListByRankFilter(String position, int limit, int offset) {
        if (null != position) {
            TypedQuery<User> query = em.createNamedQuery("user.by.rank.filter", User.class);
            query.setParameter("position", "%" + position + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<User> query = em.createNamedQuery("user.list", User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<User> getUsersList(int limit, int offset) {
        TypedQuery<User> query = em.createNamedQuery("user.list", User.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    //ezt meg at kell gondolni, jo-e igy
    public User getUserByUsername(String username) {
        if (null != username) {
            try {
                TypedQuery<User> query = em.createNamedQuery("user.by.username", User.class).setParameter("name", username);
                return query.getSingleResult();
            } catch (Exception ex) {
                return null;
            }
        }
        throw new BadRequestException("Username has not been defined correctly.");
    }

    public List<Role> getRolesListByUserId(Long userId) {
        TypedQuery<Role> query = em.createNamedQuery("user.list.roles.by.id", Role.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    //szerintem kellene... egy kulon jobb oldalso gombbal
    public List<Note> getNotesListByUserId(Long userId) {
        TypedQuery<Note> query = em.createNamedQuery("user.list.notes.by.id", Note.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    public List<Event> getEventsListByUserId(Long userId, int limit, int offset) {
        TypedQuery<Event> query = em.createNamedQuery("user.list.events.by.id", Event.class);
        query.setParameter("id", userId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

}
