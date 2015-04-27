package com.epam.gyozo_karer.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.ParkingEvent;

public interface ParkingEventService {
	/**
     * Returns the private persons that has parking events starting after the from and ends before to date
     * @param from
     * @param to
     * @return
     */
    public List<Customer> searchPrivatePersonByParkingEvent(Date from, Date to);

    /**
     * Returns the parking events that starts after the from and ends before to date for a specific customer
     * @param from
     * @param to
     * @param customer
     * @return
     */
    public List<ParkingEvent> searchParkingEvent(Date from, Date to, Customer customer);

    /**
     * Returns the parking events that starts after the from and ends before to date.
     * @param from
     * @param to
     * @return
     */
    public List<ParkingEvent> searchParkingEvent(Date from, Date to);
    
    public void createParkingEvent(Customer customer, Calendar startDate, Calendar endDate);

}
