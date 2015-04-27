package com.epam.exercise.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table (name="parkingevent")
@Component
public class ParkingEvent {

    @Column (name="startdate")
    private Date startDate;
    @Column (name="enddate")
    private Date endDate;
    @Column (name="customer")
    private Customer customer;

    public ParkingEvent() {
    }

    public ParkingEvent(Date startDate, Date endDate, Customer customer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}