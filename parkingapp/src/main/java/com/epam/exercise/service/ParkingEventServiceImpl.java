package com.epam.exercise.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.exercise.db.DBDao;
import com.epam.exercise.entity.Customer;
import com.epam.exercise.entity.ParkingEvent;
import com.epam.exercise.service_api.ParkingEventService;

@Service
public class ParkingEventServiceImpl implements ParkingEventService {

    @Autowired
    private DBDao dbDao;

    public List<ParkingEvent> searchParkingEvent(Date from, Date to, Customer customer) {
        return dbDao.getParkings(from, to, customer);
    }

    public List<ParkingEvent> searchParkingEvent(Date from, Date to) {
        return dbDao.getParkings(from, to);
    }
}