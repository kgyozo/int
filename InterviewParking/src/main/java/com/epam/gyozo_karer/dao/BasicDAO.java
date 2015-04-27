package com.epam.gyozo_karer.dao;

public interface BasicDAO<T> {
	void create(T entity);
    void edit(T entity);
    void destroy(Long id);
}
