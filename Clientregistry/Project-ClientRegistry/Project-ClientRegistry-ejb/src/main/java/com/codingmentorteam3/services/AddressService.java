package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.AddressDaoImpl;
import com.codingmentorteam3.entities.Address;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class AddressService {

    @Inject
    private AddressDaoImpl addressDaoImpl;
    
    public void createAddress(Address address) {
        addressDaoImpl.create(address);
    }
    
    public Address getAddress(Long addressId){
        return addressDaoImpl.read(addressId);
    }
    
    public Address editAddress(Address address){
        return addressDaoImpl.update(address);
    }
    
    public Address deleteAddress(Address address) {
        return addressDaoImpl.delete(address);
    }
    
}
