package com.epam.gyozo_karer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.epam.gyozo_karer.entity.Company;
import com.epam.gyozo_karer.entity.ParkingEvent;
import com.epam.gyozo_karer.entity.Person;

public class CompanyDAOImpl implements CompanyDAO {

	@PersistenceContext//(type = PersistenceContextType.EXTENDED)
    EntityManager em;
	
//	@Transactional
	public void create(Company company) {
		em.persist(company);
		em.flush();
	}

//	@Transactional
	public void edit(Company company) {
		em.merge(company);
		em.flush();
	}

	public void destroy(Long id) {
		em.remove(em.find(Company.class, id));
	}

	@Override
	public int getCompanyCount() {
		Query q = em.createQuery("select count(c) from Company as c");
        return ((Long) q.getSingleResult()).intValue();
	}

	@Override
//	@Transactional
	public Company findCompanyById(Long id) {
		 return em.find(Company.class, id);
	}

	@Override
	public List<Company> findCompanies() {
		Query q = em.createQuery("select object(c) from Company as c");
        return q.getResultList();
	}

}
