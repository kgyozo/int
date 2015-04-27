package com.epam.exercise.db;

import java.util.Date;
import java.util.List;

import com.epam.exercise.entity.Customer;
import com.epam.exercise.entity.ParkingEvent;

public interface DBDao {

    public abstract void addParking(ParkingEvent parkingEvent);

    public abstract List<ParkingEvent> getParkings(Date from, Date to);

    public abstract List<ParkingEvent> getParkings(Date from, Date to, Customer customer);

    public abstract void addCustomer(Customer customer);
}