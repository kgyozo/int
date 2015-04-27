package com.epam.gyozo_karer.dao;

import java.util.List;

import com.epam.gyozo_karer.entity.Person;

public interface PersonDAO extends BasicDAO<Person> {
	int getPersonCount();
	Person findPersonById(Long id);
    List<Person> findPersons();
}
