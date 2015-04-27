package com.epam.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exercise.db.DBDao;
import com.epam.exercise.entity.Customer;
import com.epam.exercise.service_api.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private DBDao dbDao;

    public void storeCustomer(Customer customer) {
        dbDao.addCustomer(customer);
    }
}