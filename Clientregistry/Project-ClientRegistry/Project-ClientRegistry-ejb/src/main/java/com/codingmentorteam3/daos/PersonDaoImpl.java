package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Person;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class PersonDaoImpl extends AbstractDao<Person> {

    public PersonDaoImpl() {
        super(Person.class);
    }

}
