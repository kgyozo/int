package com.epam.gyozo_karer.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;




import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.ParkingEventDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.ParkingEvent;
import com.epam.gyozo_karer.entity.Person;

public class ParkingEventServiceImpl implements ParkingEventService {
	
	private CompanyDAO companyDao;
	private PersonDAO personDao;
	private ParkingEventDAO parkingEventDao;
	private CustomerService customerService;
	
	public ParkingEventServiceImpl(CompanyDAO companyDao, PersonDAO personDao, ParkingEventDAO parkingEventDao, CustomerService customerService) {
		this.companyDao = companyDao;
		this.personDao = personDao;
		this.parkingEventDao = parkingEventDao;
		this.customerService = customerService;
	}

	@Override
	@Transactional
	public List<Customer> searchPrivatePersonByParkingEvent(Date from, Date to) {
		List<ParkingEvent> events = parkingEventDao.getPersonsParkingEventsBetweenDates(from, to);
		Set<Customer> privatePersons = new HashSet<>();
		for (ParkingEvent event : events) {
			if (event.getCustomer() instanceof Person) {
				privatePersons.add(event.getCustomer());
			}
		}
		
		List<Customer> distinctPersons = new LinkedList<>();
		distinctPersons.addAll(privatePersons);
		return distinctPersons;
	}

	@Override
	@Transactional
	public List<ParkingEvent> searchParkingEvent(Date from, Date to,
			Customer customer) {
		return parkingEventDao.getCustomerParkingEventsBetweenDates(from, to, customer);
	}

	@Override
	@Transactional
	public List<ParkingEvent> searchParkingEvent(Date from, Date to) {
		return parkingEventDao.getPersonsParkingEventsBetweenDates(from, to);
	}

	@Override
	@Transactional
	public void createParkingEvent(Customer customer, Calendar startDate,
			Calendar endDate) {
    	customerService.storeCustomer(customer);
    	ParkingEvent parkingEvent = new ParkingEvent(customer, startDate, endDate);
    	customer.addParkingEvent(parkingEvent);
    	parkingEventDao.create(parkingEvent);
    	customerService.storeCustomer(customer);
	}

}
