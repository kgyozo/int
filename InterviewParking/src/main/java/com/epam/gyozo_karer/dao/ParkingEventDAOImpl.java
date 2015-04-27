package com.epam.gyozo_karer.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.epam.gyozo_karer.entity.Customer;
import com.epam.gyozo_karer.entity.ParkingEvent;
import com.epam.gyozo_karer.entity.Person;

public class ParkingEventDAOImpl implements ParkingEventDAO {

	@PersistenceContext//(type = PersistenceContextType.EXTENDED)
    EntityManager em;
	
//	@Resource
//	private PlatformTransactionManager txManager;
	
//	@Transactional
	public void create(ParkingEvent parkingEvent) {
//		TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
		em.persist(parkingEvent);
		em.flush();
//		txManager.commit(status);
	}

//	@Transactional
	public void edit(ParkingEvent parkingEvent) {
		em.merge(parkingEvent);
		em.flush();
	}

	public void destroy(Long id) {
		em.remove(em.find(ParkingEvent.class, id));
	}
	
	public List<ParkingEvent> getPersonsParkingEventsBetweenDates(Date startDate, Date endDate) {
//		Calendar startCalendar = Calendar.getInstance();
//		startCalendar.setTime(startDate);
		
		Query query = em.createQuery("select p from ParkingEvent p where p.startDate >= :start " 
				+ "and p.endDate <= :end" );
		query.setParameter("start", startDate, TemporalType.TIMESTAMP);
		query.setParameter("end", endDate, TemporalType.TIMESTAMP);
		
		
		return query.getResultList();
	}

	public List<ParkingEvent> getCustomerParkingEventsBetweenDates(Date startDate, Date endDate, Customer customer) {
		Query query = em.createQuery("select p from ParkingEvent p where p.startDate >= :start " 
				+ "and p.endDate <= :end  and p.customer = :customer");
		query.setParameter("start", startDate, TemporalType.TIMESTAMP);
		query.setParameter("end", endDate, TemporalType.TIMESTAMP);
		query.setParameter("customer", customer);
		
		return query.getResultList();
	}

}
