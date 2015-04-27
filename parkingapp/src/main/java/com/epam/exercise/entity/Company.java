package com.epam.exercise.entity;

import org.springframework.stereotype.Component;

@Component
public class Company extends Customer {
    
    private String companyName;
    private int taxID;
    
    public Company() {
        super("company");
    }
    
    public Company(String companyName, int taxID) {
        this();
        this.companyName = companyName;
        this.taxID = taxID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getTaxID() {
        return taxID;
    }

    public void setTaxID(int taxID) {
        this.taxID = taxID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + taxID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Company)) {
            return false;
        }
        Company other = (Company) obj;
        if (companyName == null) {
            if (other.companyName != null) {
                return false;
            }
        } else if (!companyName.equals(other.companyName)) {
            return false;
        }
        if (taxID != other.taxID) {
            return false;
        }
        return true;
    }
}