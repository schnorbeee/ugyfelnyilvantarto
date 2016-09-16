package com.codingmentorteam3.entities;

import com.codingmentorteam3.beans.AddressBean;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "address_table")
@NamedQueries ({
    @NamedQuery(name = "address.list", query = "SELECT a FROM address_table a"),
    @NamedQuery(name = "address.by.all.parameters", query = "SELECT a FROM address_table a WHERE a.city =:city AND a.country =:country AND a.zipCode =:zip AND a.street =:street AND a.houseNumber =:houseNumber")
})
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(nullable = false, length = 30)
    private String country;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 30)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    public Address() {
        //default contructor
    }

    public Address(AddressBean newAddress) {
        this.zipCode = newAddress.getZipCode();
        this.country = newAddress.getCountry();
        this.city = newAddress.getCity();
        this.street = newAddress.getStreet();
        this.houseNumber = newAddress.getHouseNumber();
    }
    
    public Address(String zipCode, String country, String city, String street, String houseNumber) {
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.zipCode);
        hash = 41 * hash + Objects.hashCode(this.country);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.street);
        hash = 41 * hash + Objects.hashCode(this.houseNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.houseNumber, other.houseNumber)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
