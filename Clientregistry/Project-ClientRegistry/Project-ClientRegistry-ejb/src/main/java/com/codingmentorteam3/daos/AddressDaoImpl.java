package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Address;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class AddressDaoImpl extends AbstractDao<Address> {

    public AddressDaoImpl() {
        super(Address.class);
    }

    public Address getAddressByAllParameters(String city, String country, String zip, String street, String houseNumber) {
        TypedQuery<Address> query = em.createNamedQuery("address.by.all.parameters", Address.class);
        query.setParameter("city", city);
        query.setParameter("country", country);
        query.setParameter("zip", zip);
        query.setParameter("street", street);
        query.setParameter("houseNumber", houseNumber);
        return query.getSingleResult();
    }
    
}
