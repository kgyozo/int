package com.epam.exercise.service_api;

import java.util.Date;
import java.util.List;

import com.epam.exercise.entity.Customer;
import com.epam.exercise.entity.ParkingEvent;

public interface ParkingEventService {

    public List<ParkingEvent> searchParkingEvent(Date from, Date to, Customer customer);

    public List<ParkingEvent> searchParkingEvent(Date from, Date to);
}