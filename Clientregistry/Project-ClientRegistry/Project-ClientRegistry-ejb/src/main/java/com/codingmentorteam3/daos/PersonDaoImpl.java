package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Person;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class PersonDaoImpl extends AbstractDao<Person> {

    public PersonDaoImpl() {
        super(Person.class);
    }

    public List<ConnectionChannel> getChannelsListByUserId(Long personId) {
        Person currentPerson = read(personId);
        if (null != currentPerson) {
            TypedQuery<ConnectionChannel> query = em.createNamedQuery("person.list.channels.by.id", ConnectionChannel.class);
            query.setParameter("id", personId);
            return query.getResultList();
        }
        throw new BadRequestException("Person is not in the database.");
    }
    
}
