package com.epam.gyozo_karer.services;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.entity.Company;
import com.epam.gyozo_karer.entity.Person;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest extends TestCase {

	@Mock
	PersonDAO personDao;
	@Mock
	CompanyDAO companyDao;
	
	@Test
	public void newPerson() {
		Person person = new Person();
		
		CustomerService service = new CustomerServiceImpl(companyDao, personDao);
		service.storeCustomer(person);
		
		verify(personDao).create(person);
	}
	
	@Test
	public void existingPerson() {
		Person person = new Person();
		person.setId(1L);
		
		CustomerService service = new CustomerServiceImpl(companyDao, personDao);
		service.storeCustomer(person);
		
		verify(personDao).edit(person);
	}
	
	@Test
	public void newCompany() {
		Company company = new Company();
		
		CustomerService service = new CustomerServiceImpl(companyDao, personDao);
		service.storeCustomer(company);
		
		verify(companyDao).create(company);
	}
	
	@Test
	public void existingCompany() {
		Company company = new Company();
		company.setId(1L);
		
		CustomerService service = new CustomerServiceImpl(companyDao, personDao);
		service.storeCustomer(company);
		
		verify(companyDao).edit(company);
	}
	
}
