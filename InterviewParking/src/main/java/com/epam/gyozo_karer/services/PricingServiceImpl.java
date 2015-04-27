package com.epam.gyozo_karer.services;

import java.util.Date;

import com.epam.gyozo_karer.dao.CompanyDAO;
import com.epam.gyozo_karer.dao.PersonDAO;
import com.epam.gyozo_karer.entity.Customer;

public class PricingServiceImpl implements PricingService {
	
	private CompanyDAO companyDao;
	private PersonDAO personDao;
	
	public PricingServiceImpl(CompanyDAO companyDao, PersonDAO personDao) {
		this.companyDao = companyDao;
		this.personDao = personDao;
	}

	public long calculateParkingFee(Date start, Date end, Customer customer) {
		return 0;
	}

}
