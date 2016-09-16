package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Address;
import java.util.List;
import javax.ejb.Stateless;

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
    
}
