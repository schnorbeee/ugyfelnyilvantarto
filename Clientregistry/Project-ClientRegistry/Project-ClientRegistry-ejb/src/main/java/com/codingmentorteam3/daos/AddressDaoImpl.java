package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Address;
import java.util.List;
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

    public List<Address> getAddressList() {
        return em.createNamedQuery("address.list", Address.class).getResultList();
    }
    
    public Address getAddressByAllParameters(String city, String country, String zip, String street, String houseNumber) {
        try {
            TypedQuery<Address> query = em.createNamedQuery("address.by.all.parameters", Address.class).setParameter("city", city).setParameter("country", country).setParameter("zip", zip).setParameter("street", street).setParameter("houseNumber", houseNumber);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
