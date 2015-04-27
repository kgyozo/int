package com.epam.gyozo_karer.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Person extends Customer {
	
	private String firstName;
	private String lastName;
	
	public Person() {
	}
	
	public Person(String firstName, String lastName, String streetName, String telphoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.setStreetName(streetName);
		this.setTelphoneNumber(telphoneNumber);
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Person: [" + firstName + ", "+ lastName + " : " + getStreetName() + "  phone: " + getTelphoneNumber() + "]";
	}
	
	
}
