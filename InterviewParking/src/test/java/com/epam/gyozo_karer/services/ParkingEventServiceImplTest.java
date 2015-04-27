package com.epam.gyozo_karer.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.ParkingEventDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.entity.Company;
import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.ParkingEvent;
import com.epam.gyozo_karer.entity.Person;

@RunWith(MockitoJUnitRunner.class)
public class ParkingEventServiceImplTest {
	
	private ParkingEventService parkingEventService;
	@Mock
	private CompanyDAO companyDao;
	@Mock
	private PersonDAO personDao;
	@Mock
	private ParkingEventDAO parkingEventDao;
	@Mock
	private CustomerService customerService;
	private Person customer;
	private Calendar startDate;
	private Calendar endDate;
	
	@Before
	public void setUp() {
		parkingEventService = new ParkingEventServiceImpl(companyDao, personDao, parkingEventDao, customerService);
		customer = new Person();
		startDate = new GregorianCalendar(2015, 1, 2, 3, 10);
		endDate = new GregorianCalendar(2015, 2, 10, 13, 10);
	}

	@Test
	public void createParkingEventWithNewCustomer() {
		parkingEventService.createParkingEvent(customer, startDate, endDate);
		
		verify(parkingEventDao).create(Mockito.any(ParkingEvent.class));
		verify(customerService, times(2)).storeCustomer(customer);
	}

	@Test
	public void createParkingEventWithExistingCustomer() {
		customer.setId(1L);
		
		parkingEventService.createParkingEvent(customer, startDate, endDate);
		
		verify(parkingEventDao).create(Mockito.any(ParkingEvent.class));
		verify(customerService, times(2)).storeCustomer(customer);
	}
	
	@Test
	public void personParkingBetweenDates() {
		Person person = new Person("test first 1", "test last 1", "test street 1", "11111111");
		Company company = new Company("test name c1", "test tax c1", "test street c1", "2222222");
		ParkingEvent event1 = new ParkingEvent(person, new GregorianCalendar(), new GregorianCalendar());
		ParkingEvent event2 = new ParkingEvent(company, new GregorianCalendar(), new GregorianCalendar());
		List<ParkingEvent> events = new LinkedList<>();
		events.add(event1);
		events.add(event2);
		Mockito.when(parkingEventDao.getPersonsParkingEventsBetweenDates(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(events);
		
		List<Customer> list = parkingEventService.searchPrivatePersonByParkingEvent(Mockito.any(Date.class), Mockito.any(Date.class));
		
		Assert.assertThat(list.size(), is(1));
		Assert.assertThat(list.get(0), instanceOf(Person.class));
	}

	@Test
	public void personParkingBetweenDatesPersonHasMoreThanOneEvents() {
		Person person = new Person("test first 1", "test last 1", "test street 1", "11111111");
		Company company = new Company("test name c1", "test tax c1", "test street c1", "2222222");
		ParkingEvent event1 = new ParkingEvent(person, new GregorianCalendar(), new GregorianCalendar());
		ParkingEvent event2 = new ParkingEvent(company, new GregorianCalendar(), new GregorianCalendar());
		ParkingEvent event3 = new ParkingEvent(person, new GregorianCalendar(), new GregorianCalendar());
		List<ParkingEvent> events = new LinkedList<>();
		events.add(event1);
		events.add(event2);
		events.add(event3);
		Mockito.when(parkingEventDao.getPersonsParkingEventsBetweenDates(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(events);
		
		List<Customer> list = parkingEventService.searchPrivatePersonByParkingEvent(Mockito.any(Date.class), Mockito.any(Date.class));
		
		Assert.assertThat(list.size(), is(1));
		Assert.assertThat(list.get(0), instanceOf(Person.class));
				
	}

}

