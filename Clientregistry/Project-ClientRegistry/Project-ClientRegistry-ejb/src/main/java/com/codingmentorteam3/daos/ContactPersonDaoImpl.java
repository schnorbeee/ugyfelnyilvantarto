package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.ContactPerson;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ContactPersonDaoImpl extends AbstractDao<ContactPerson> {

    public ContactPersonDaoImpl() {
        super(ContactPerson.class);
    }

    public List<ConnectionChannel> getChannelsByContecterId(Long contacterId) {
        ContactPerson current = read(contacterId);
        if (null != current) {
            try {
                return em.createNamedQuery("contact.person.channels.by.id", ConnectionChannel.class).setParameter("id", contacterId).getResultList();
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this contacter in database.");
    }

}
