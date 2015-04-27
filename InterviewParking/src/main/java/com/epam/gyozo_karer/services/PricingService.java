package com.epam.gyozo_karer.services;

import java.util.Date;

import com.epam.gyozo_karer.entity.Customer;

public interface PricingService {
	/**
     * Calculates the price of the parking based on the following price list :
     *
     * Hourly parking fee during week days for : company / private person :
     *     100 / 120
     * Every started hour costs a full hour, First hour is free.
     *
     * Daily parking fee on weekend for : company / private person :
     *     500 / 600
     * Every started day costs a full day
     *
     * Public holidays are not treated differently (we only care about week days and weekend days)
     *
     * Examples :
     *  Private person, 59 minutes week day : 0
     *  Private person, 61 minutes week day : 120 (one free hour + one paid hour)
     *  Private person, 59 minutes week day + 1 week end day : 600 (one free hour + one weekend day)
     *
     * @param start
     * @param end
     * @return the price of the parking event
     */
    public long calculateParkingFee(Date start, Date end, Customer customer);


}
