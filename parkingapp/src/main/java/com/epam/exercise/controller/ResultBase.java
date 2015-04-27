package com.epam.exercise.controller;

import com.epam.exercise.entity.ParkingEvent;

public class ResultBase {

    private ParkingEvent parkingEvent;
    private long price;

    public ResultBase(ParkingEvent parkingEvent, long price) {
        this.parkingEvent = parkingEvent;
        this.price = price;
    }

    public ParkingEvent getParkingEvent() {
        return parkingEvent;
    }

    public long getPrice() {
        return price;
    }
}