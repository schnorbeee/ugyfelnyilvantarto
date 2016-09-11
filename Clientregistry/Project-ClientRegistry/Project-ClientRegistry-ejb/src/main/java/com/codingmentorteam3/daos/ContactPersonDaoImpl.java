package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EmptyListException;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ContactPersonDaoImpl extends AbstractDao<ContactPerson> {

    public ContactPersonDaoImpl() {
        super(ContactPerson.class);
    }

    public List<ConnectionChannel> getChannelsByContacterId(Long contacterId) {
        ContactPerson current = read(contacterId);
        if (null != current) {
            List<ConnectionChannel> query = em.createNamedQuery("contact.person.channels.by.id", ConnectionChannel.class).setParameter("id", contacterId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This contact person has no connection channel.");
            }
            return query;
        }
        throw new BadRequestException("We haven't got this contacter in database.");
    }

}
