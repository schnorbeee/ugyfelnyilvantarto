package com.codingmentorteam3.dtos;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
public class CompanyDTO {
    
    private static final String DEFAULT_LOGO = "";
    
    @NotNull 
    @Size(min = 2, max = 30)
    private String name;
    
    @NotNull
    private Address address;

    @Pattern(regexp = "\\d{8}-\\d{1}-\\d{2}")
    private String taxNumber;
    
    @NotNull
    private String logo = DEFAULT_LOGO;

    public CompanyDTO() {
        // Default constructor
    }

    public CompanyDTO(Company company) {
        this.name = company.getName();
        this.address = company.getAddress();
        this.taxNumber = company.getTaxNumber();
        this.logo = company.getLogo();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.address);
        hash = 17 * hash + Objects.hashCode(this.taxNumber);
        hash = 17 * hash + Objects.hashCode(this.logo);
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
        final CompanyDTO other = (CompanyDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.logo, other.logo)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.taxNumber, other.taxNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" + "name=" + name + ", address=" + address + ", taxNumber=" + taxNumber + ", logo=" + logo + '}';
    }
    
}
