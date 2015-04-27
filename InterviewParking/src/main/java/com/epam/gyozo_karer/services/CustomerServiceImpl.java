package com.epam.gyozo_karer.services;

import org.springframework.transaction.annotation.Transactional;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.entity.Company;
import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.Person;

public class CustomerServiceImpl implements CustomerService {
	
	private CompanyDAO companyDao;
	private PersonDAO personDao;
	
	public CustomerServiceImpl(CompanyDAO companyDao, PersonDAO personDao) {
		this.companyDao = companyDao;
		this.personDao = personDao;
	}

	@Transactional
	public void storeCustomer(Customer customer) {
		if (customer instanceof Person) {
			Person person = (Person) customer;
			if (person.getId() == null) {
				personDao.create(person);
			} else {
				personDao.edit(person);
			}
		} else if (customer instanceof Company) {
			Company company = (Company) customer;
			if (company.getId() == null) {
				companyDao.create(company);
			} else {
				companyDao.edit(company);
			}
		} 

	}

}
