package com.epam.exercise.service_api;

import java.util.Date;

import com.epam.exercise.entity.Customer;

public interface PricingService {

    public static final int COMPANY_HOUR_FEE = 100;
    public static final int PRIVATE_HOUR_FEE = 120;
    public static final int COMPANY_DAY_FEE = 500;
    public static final int PRIVATE_DAY_FEE = 600;

    public long calculateParkingFee(Date start, Date end, Customer customer);
}
