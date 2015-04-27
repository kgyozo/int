package com.epam.gyozo_karer.dao;

import java.util.Date;
import java.util.List;

import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.ParkingEvent;

public interface ParkingEventDAO extends BasicDAO<ParkingEvent>{

	public List<ParkingEvent> getPersonsParkingEventsBetweenDates(Date startDate, Date endDate);
	public List<ParkingEvent> getCustomerParkingEventsBetweenDates(Date startDate, Date endDate, Customer customer);
}
