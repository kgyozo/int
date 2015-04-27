package com.epam.exercise.entity;

public abstract class Customer {
    
    private String telephoneNumber;
    private String streetName;
    private String type;
    
    public Customer(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
        result = prime * result + ((telephoneNumber == null) ? 0 : telephoneNumber.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;
        if (streetName == null) {
            if (other.streetName != null) {
                return false;
            }
        } else if (!streetName.equals(other.streetName)) {
            return false;
        }
        if (telephoneNumber == null) {
            if (other.telephoneNumber != null) {
                return false;
            }
        } else if (!telephoneNumber.equals(other.telephoneNumber)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
}