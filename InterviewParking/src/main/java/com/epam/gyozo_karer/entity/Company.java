package com.epam.gyozo_karer.entity;

import javax.persistence.Entity;

@Entity
public class Company extends Customer {

	private String companyName;
	private String taxId;

	public Company() {
	}
	
	public Company(String companyName, String taxId, String streetName, String telphoneNumber) {
		this.companyName = companyName;
		this.taxId = taxId;
		this.setStreetName(streetName);
		this.setTelphoneNumber(telphoneNumber);
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
	@Override
	public String toString() {
		return "Person: [" + companyName + " ("+ taxId + ") : " + getStreetName() + "  phone: " + getTelphoneNumber() + "]";
	}
	
}
