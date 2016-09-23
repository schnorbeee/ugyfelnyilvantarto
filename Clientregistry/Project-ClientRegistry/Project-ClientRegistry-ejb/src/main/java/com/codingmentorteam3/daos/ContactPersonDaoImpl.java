package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.ContactPerson;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ContactPersonDaoImpl extends AbstractDao<ContactPerson> {

    public ContactPersonDaoImpl() {
        super(ContactPerson.class);
    }

    public List<ContactPerson> getContactPersonList(int limit, int offset) {
        TypedQuery<ContactPerson> query = em.createNamedQuery("contact.person.list", ContactPerson.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<ConnectionChannel> getChannelsByContacterId(Long contacterId) {
        TypedQuery<ConnectionChannel> query = em.createNamedQuery("contact.person.channels.by.id", ConnectionChannel.class);
        query.setParameter("id", contacterId);
        return query.getResultList();
    }

}
