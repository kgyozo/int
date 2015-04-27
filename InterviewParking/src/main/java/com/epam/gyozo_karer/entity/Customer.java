package com.epam.gyozo_karer.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

//@MappedSuperclass
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Customer {
	
	
    private Long id; 
	
	private List<ParkingEvent> parkingEvents;// = new LinkedList<>();
	private String telphoneNumber;
	private String streetName;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany//(mappedBy="customer")
	public List<ParkingEvent> getParkingEvents() {
		return parkingEvents;
	}

	public void setParkingEvents(List<ParkingEvent> parkingEvents) {
		this.parkingEvents = parkingEvents;
	}

	public String getTelphoneNumber() {
		return telphoneNumber;
	}

	public void setTelphoneNumber(String telphoneNumber) {
		this.telphoneNumber = telphoneNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public void addParkingEvent(ParkingEvent parkingEvent) {
		if (parkingEvents == null) {
			parkingEvents = new LinkedList<>();
		}
		parkingEvents.add(parkingEvent);
	}

}
