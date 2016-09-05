package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.NumItemsPerPageType;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class UserDaoImpl extends AbstractDao<User> {

    private static final String BADREQUESTMESSAGE = "We haven't got this user in database.";

    public UserDaoImpl() {
        super(User.class);
    }

    public List<User> getUsersListByUsernameFilter(String username) {
        try {
            List<User> query = em.createNamedQuery("user.by.username.filter", User.class).setParameter("name", username).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsersListByFirstNameFilter(String firstName) {
        try {
            List<User> query = em.createNamedQuery("user.by.firstname.filter", User.class).setParameter("first", firstName).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsersListByLastNameFilter(String lastName) {
        try {
            List<User> query = em.createNamedQuery("user.by.lastname.filter", User.class).setParameter("last", lastName).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsersListByRankFilter(String rank) {
        try {
            List<User> query = em.createNamedQuery("user.by.rank.filter", User.class).setParameter("rank", rank).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsersList() {
        try {
            List<User> query = em.createNamedQuery("user.list", User.class).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            User query = em.createNamedQuery("user.by.username", User.class).setParameter("name", username).getSingleResult();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NumItemsPerPageType> getNumItemPerPageToPageableTablesByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<NumItemsPerPageType> query = em.createNamedQuery("user.num.item.per.page.by.id", NumItemsPerPageType.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Role> getRolesListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<Role> query = em.createNamedQuery("user.list.roles.by.id", Role.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Invitation> getSentInvitationsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<Invitation> query = em.createNamedQuery("user.list.invitation.sent.by.id", Invitation.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Invitation> getReceivedInvitationsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<Invitation> query = em.createNamedQuery("user.list.invitation.received.by.id", Invitation.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Note> getNotesListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<Note> query = em.createNamedQuery("user.list.notes.by.id", Note.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Event> getEventsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<Event> query = em.createNamedQuery("user.list.events.by.id", Event.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<ConnectionChannel> getChannelsListByUserId(Long userId) {
        User current = read(userId);
        if (null != current) {
            try {
                List<ConnectionChannel> query = em.createNamedQuery("user.list.channels.by.id", ConnectionChannel.class).setParameter("id", userId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

}
