package com.epam.exercise.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.exercise.entity.Company;
import com.epam.exercise.entity.Customer;
import com.epam.exercise.entity.ParkingEvent;
import com.epam.exercise.entity.PrivatePerson;
import com.epam.exercise.service_api.ParkingEventService;
import com.epam.exercise.service_api.PricingService;

@RestController
@RequestMapping("/parkingapp")
public class BaseController {

    @Autowired
    private ParkingEventService pes;

    @Autowired
    PricingService pricingService;

    @RequestMapping("/search")
    public List<ResultBase> search(@RequestParam(value = "fromDate", defaultValue = "2010-01-01") String fromDate,
            @RequestParam(value = "toDate", defaultValue = "2020-12-31") String toDate,
            @RequestParam(value = "customer", defaultValue = "private") String customer) {
        Date from = null;
        Date to = null;
        try {
            from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Customer cus = null;
        switch (customer) {
        case "company":
            cus = new Company();
            break;
        case "private":
            cus = new PrivatePerson();
            break;
        default:
            cus = new PrivatePerson();
            break;
        }
        List<ParkingEvent> parkingEventList = pes.searchParkingEvent(from, to, cus);
        List<ResultBase> list = new ArrayList<ResultBase>();
        for (ParkingEvent e : parkingEventList) {
            list.add(new ResultBase(e, pricingService.calculateParkingFee(e.getStartDate(), e.getEndDate(),
                    e.getCustomer())));
        }
        return list;
    }
}