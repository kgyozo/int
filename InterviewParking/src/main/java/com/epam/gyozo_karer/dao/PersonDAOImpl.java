package com.epam.gyozo_karer.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.epam.gyozo_karer.entity.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@PersistenceContext
    EntityManager em;
	
//	@Transactional
	public void create(Person person) {
		em.persist(person);
		em.flush();
	}

//	@Transactional
	public void edit(Person person) {
		em.merge(person);
		em.flush();
	}
	

	public void destroy(Long id) {
		em.remove(em.find(Person.class, id));		
	}

    public int getPersonCount() {
        Query q = em.createQuery("select count(p) from Person as p");
        return ((Long) q.getSingleResult()).intValue();
    }

//    @Transactional
    public Person findPersonById(Long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findPersons() {
    	Query q = em.createQuery("select object(p) from Person as p");
        return q.getResultList();
    }

}
