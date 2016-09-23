package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.ContactPersonDaoImpl;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.ContactPerson;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ContactPersonService {

    @Inject
    private ContactPersonDaoImpl contactPersonDaoImpl;

    public void createContactPerson(ContactPerson contactPerson) {
        contactPersonDaoImpl.create(contactPerson);
    }

    public ContactPerson getContactPerson(Long contactPersonId) {
        return contactPersonDaoImpl.read(contactPersonId);
    }

    public ContactPerson editContactPerson(ContactPerson contactPerson) {
        return contactPersonDaoImpl.update(contactPerson);
    }

    public ContactPerson deleteContactPerson(ContactPerson contactPerson) {
        return contactPersonDaoImpl.delete(contactPerson);
    }

    public List<ContactPerson> getContactPersonList(Integer limit, Integer offset) {
        return contactPersonDaoImpl.getContactPersonList(limit, offset);
    }

    public List<ConnectionChannel> getChannelsByContacterId(Long contacterId) {
        return contactPersonDaoImpl.getChannelsByContacterId(contacterId);
    }

}
