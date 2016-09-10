package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Address;
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

}
