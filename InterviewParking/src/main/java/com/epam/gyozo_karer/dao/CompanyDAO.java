package com.epam.gyozo_karer.dao;

import java.util.List;

import com.epam.gyozo_karer.entity.Company;

public interface CompanyDAO extends BasicDAO<Company>{
	int getCompanyCount();
	Company findCompanyById(Long id);
    List<Company> findCompanies();
}
