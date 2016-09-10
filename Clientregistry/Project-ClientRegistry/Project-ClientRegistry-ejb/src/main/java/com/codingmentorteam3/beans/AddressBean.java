package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "address")
public class AddressBean implements Serializable {
    
    @NotNull 
    @Size(min = 3, max = 30)
    private String country;
    
    @NotNull 
    @Size(min = 2, max = 30)
    private String city;
    
    @NotNull 
    @Size(max = 30)
    private String street;
    
    @NotNull 
    @Pattern(regexp = "\\d{1,4}(\\/[A-Z]){0,1}") // e.g. 12, 123/A
    private String houseNumber;
    
    @NotNull 
    @Pattern(regexp = "[A-Z]{1,3}\\-\\d{4,8}") // e.g. HUN-1234, ES-123456
    private String zipCode;

    public AddressBean() {
        // Default constructor
    }

    public AddressBean(String country, String city, String street, String houseNumber, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.country);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.street);
        hash = 97 * hash + Objects.hashCode(this.houseNumber);
        hash = 97 * hash + Objects.hashCode(this.zipCode);
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
        final AddressBean other = (AddressBean) obj;
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
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddressBean{" + "country=" + country + ", city=" + city + ", street=" + street + ", houseNumber=" + houseNumber + ", zipCode=" + zipCode + '}';
    }
    
}
