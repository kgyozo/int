package com.epam.gyozo_karer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.CompanyDAOImpl;
import com.epam.gyozo_karer.dao.ParkingEventDAO;
import com.epam.gyozo_karer.dao.ParkingEventDAOImpl;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.dao.PersonDAOImpl;

public class SpringConfigurationDAO {

	@Bean
	public CompanyDAO companyDao() {
		return new CompanyDAOImpl();
	}
	
	@Bean
	public PersonDAO personDao() {
		return new PersonDAOImpl();
	}
	
	@Bean
	public ParkingEventDAO parkingEventDao() {
		return new ParkingEventDAOImpl();
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor petpp() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}

