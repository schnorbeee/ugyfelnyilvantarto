package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.PersonDaoImpl;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bicsak Daniel
 */
@Stateless
public class PersonService {

    @Inject
    private PersonDaoImpl personDao;

    public void createPerson(Person person) {
        personDao.create(person);
    }

    public Person getPerson(Long personId) {
        return personDao.read(personId);
    }

    public Person editPerson(Person person) {
        return personDao.update(person);
    }

    public Person deletePerson(Person person) {
        return personDao.delete(person);
    }
    
    public List<ConnectionChannel> getChannelsListByUserId(Long userId) {
        return personDao.getChannelsListByUserId(userId);
    }
    
}
