package com.epam.gyozo_karer.services;

import com.epam.gyozo_karer.entity.Customer;

public interface CustomerService {
    /**
     * Stores a customer to the DB
     * @param customer
     */
    public void storeCustomer(Customer customer);

}
