package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.Address;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "companyBean")
public class CompanyBean {

    private static final String DEFAULT_LOGO = "";

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private Address address;

    @Pattern(regexp = "\\d{8}-\\d{1}-\\d{2}", message = "Invalid tax-number format. Please enter a tax-number matching this pattern: xxxxxxxx-x-xx")
    private String taxNumber;

    @NotNull
    private String logo = DEFAULT_LOGO;
    
    private String searchString;

    public CompanyBean() {
        // Default constructor
    }

    public CompanyBean(String name, Address address, String taxNumber) {
        this.name = name;
        this.address = address;
        this.taxNumber = taxNumber;
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

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
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
        final CompanyBean other = (CompanyBean) obj;
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
        return "CompanyBean{" + "name=" + name + ", address=" + address + ", taxNumber=" + taxNumber + ", logo=" + logo + '}';
    }

}
