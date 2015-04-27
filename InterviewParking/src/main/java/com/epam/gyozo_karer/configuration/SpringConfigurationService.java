package com.epam.gyozo_karer.configuration;

import org.springframework.context.annotation.Bean;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.ParkingEventDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.services.CustomerService;
import com.epam.gyozo_karer.services.CustomerServiceImpl;
import com.epam.gyozo_karer.services.ParkingEventService;
import com.epam.gyozo_karer.services.ParkingEventServiceImpl;
import com.epam.gyozo_karer.services.PricingService;
import com.epam.gyozo_karer.services.PricingServiceImpl;

public class SpringConfigurationService {

	@Bean
	public CustomerService customerService(CompanyDAO companyDao, PersonDAO personDao) {
		return new CustomerServiceImpl(companyDao, personDao);
	}
	
	@Bean
	public ParkingEventService parkingEventService(CompanyDAO companyDao, PersonDAO personDao, ParkingEventDAO parkingEventDao, CustomerService customerService) {
		return new ParkingEventServiceImpl(companyDao, personDao, parkingEventDao, customerService);
	}
	
	@Bean
	public PricingService pricingService(CompanyDAO companyDao, PersonDAO personDao) {
		return new PricingServiceImpl(companyDao, personDao);
	}
}
