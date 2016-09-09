package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.NumItemsPerPageType;
import com.codingmentorteam3.exceptions.BadRequestException;
import com.codingmentorteam3.exceptions.EmptyListException;
import com.codingmentorteam3.exceptions.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class UserDaoImpl extends AbstractDao<User> {

    private static final String BADREQUESTMESSAGE = "User is not in the database.";

    public UserDaoImpl() {
        super(User.class);
    }

    public List<User> getUsersListByUsernameFilter(String username) {
        if (null != username) {
            List<User> query = em.createNamedQuery("user.by.username.filter", User.class).setParameter("name", "%" + username + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + username);
            }
            return query;
        }
        return em.createNamedQuery("user.list", User.class).getResultList();
    }

    public List<User> getUsersListByFirstNameFilter(String firstName) {
        if (null != firstName) {
            List<User> query = em.createNamedQuery("user.by.firstname.filter", User.class).setParameter("first", "%" + firstName + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + firstName);
            }
            return query;
        }
        return em.createNamedQuery("user.list", User.class).getResultList();
    }

    public List<User> getUsersListByLastNameFilter(String lastName) {
        if (null != lastName) {
            List<User> query = em.createNamedQuery("user.by.lastname.filter", User.class).setParameter("last", "%" + lastName + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + lastName);
            }
            return query;
        }
        return em.createNamedQuery("user.list", User.class).getResultList();
    }

    public List<User> getUsersListByRankFilter(String rank) {
        if (null != rank) {
            List<User> query = em.createNamedQuery("user.by.rank.filter", User.class).setParameter("rank", "%" + rank + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + rank);
            }
            return query;
        }
        return em.createNamedQuery("user.list", User.class).getResultList();
    }

    public List<User> getUsersList() {
        List<User> query = em.createNamedQuery("user.list", User.class).getResultList();
        if (query.isEmpty()) {
            throw new EmptyListException("The user list is empty now.");
        }
        return query;
    }

    public User getUserByUsername(String username) {
        if (null != username) {
            User query = em.createNamedQuery("user.by.username", User.class).setParameter("name", username).getSingleResult();
            if (null != query) {
                throw new EmptyListException("We haven't this user with this username in database.");
            }
            return query;
        }
        throw new BadRequestException("Username what you want is bad defined.");
    }

    public List<NumItemsPerPageType> getNumItemPerPageToPageableTablesByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<NumItemsPerPageType> query = em.createNamedQuery("user.num.item.per.page.by.id", NumItemsPerPageType.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("We haven't defined for this user num/page.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Role> getRolesListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<Role> query = em.createNamedQuery("user.list.roles.by.id", Role.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This user haven't any role.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Invitation> getSentInvitationsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<Invitation> query = em.createNamedQuery("user.list.invitation.sent.by.id", Invitation.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This user haven't any sent invitation.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Invitation> getReceivedInvitationsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<Invitation> query = em.createNamedQuery("user.list.invitation.received.by.id", Invitation.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This user haven't any recieved invitation.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Note> getNotesListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<Note> query = em.createNamedQuery("user.list.notes.by.id", Note.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This user haven't any note.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Event> getEventsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<Event> query = em.createNamedQuery("user.list.events.by.id", Event.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This user haven't any event.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<ConnectionChannel> getChannelsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            List<ConnectionChannel> query = em.createNamedQuery("user.list.channels.by.id", ConnectionChannel.class).setParameter("id", userId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This user haven't any connection channel.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

}
