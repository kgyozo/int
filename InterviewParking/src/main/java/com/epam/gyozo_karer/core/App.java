package com.epam.gyozo_karer.core;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.epam.gyozo_karer.configuration.SpringConfigurationDAO;
import com.epam.gyozo_karer.configuration.SpringConfigurationJPA;
import com.epam.gyozo_karer.configuration.SpringConfigurationService;
import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.ParkingEventDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.entity.Company;
import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.ParkingEvent;
import com.epam.gyozo_karer.entity.Person;
import com.epam.gyozo_karer.services.CustomerService;
import com.epam.gyozo_karer.services.ParkingEventService;

/**
 * Hello world!
 *
 */
public class App {
	
	public PersonDAO personDao;
	private CompanyDAO companyDao;
	public ParkingEventService parkingEventService;
	private ParkingEventDAO parkingEventDAO;
	private CustomerService customerService;
	private static Person p1;
	
	public App() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfigurationJPA.class, 
				SpringConfigurationDAO.class,
				SpringConfigurationService.class
				);

		parkingEventService = context.getBean(ParkingEventService.class);
		customerService = context.getBean(CustomerService.class);
		personDao = context.getBean(PersonDAO.class);
		companyDao = context.getBean(CompanyDAO.class);
		parkingEventDAO = context.getBean(ParkingEventDAO.class);
	}
	
    public static void main( String[] args )
    {
    	App app = new App();
    	app.populate();
    	
    	Person p = new Person("Bela", "Pelda1", "cim pelda 1", "1111111");
    	app.customerService.storeCustomer(p);
    	
    	p.setLastName("Pelda11");
    	app.customerService.storeCustomer(p);
    	
    	Company c = new Company("companyName test", "taxId 11111 test", "streetName test", "222222222");
    	app.customerService.storeCustomer(c);
    	
    	Date start = new Date(115, 1, 1);
		Date end = new Date(115, 2, 31);
		List<Customer> lists = app.parkingEventService.searchPrivatePersonByParkingEvent(start, end);
		for (Customer customer : lists ) {
			System.out.println(customer);
		}
		
		List<ParkingEvent> events = app.parkingEventService.searchParkingEvent(start, end, p1);
		for (ParkingEvent event : events ) {
			System.out.println(event.getStartDate() + " --> " + event.getEndDate());
		}

    }
    
    private void populate() {
    	p1 = new Person("Dome", "Kiss", "cime 1", "+36707070123");
    	customerService.storeCustomer(p1);
    	
    	Person p2 = new Person("Istvan", "Nagy", "cime 2", "+36702345678");
    	customerService.storeCustomer(p2);
    	
    	Company c1 = new Company("companyName", "taxId", "cegcime 1", "+36707071123");
    	customerService.storeCustomer(c1);
    	
    	Calendar s1 = new GregorianCalendar(2015, 1, 2, 12, 00);
    	Calendar e1 = new GregorianCalendar(2015, 1, 2, 14, 00);
    	parkingEventService.createParkingEvent(p1, s1, e1);
    	
    	Calendar s2 = new GregorianCalendar(2015, 2, 2, 12, 00);
    	Calendar e2 = new GregorianCalendar(2015, 2, 2, 16, 00);
    	parkingEventService.createParkingEvent(p1, s2, e2);

    	Calendar s3 = new GregorianCalendar(2015, 2, 12, 12, 00);
    	Calendar e3 = new GregorianCalendar(2015, 2, 12, 16, 00);
    	parkingEventService.createParkingEvent(c1, s3, e3);

    	Person p3 = new Person("Pistu", "Toth", "cime 3", "+36702345678");
    	Calendar s4 = new GregorianCalendar(2015, 3, 2, 11, 30);
    	Calendar e4 = new GregorianCalendar(2015, 3, 3, 16, 150);
    	parkingEventService.createParkingEvent(p3, s4, e4);

    }
}