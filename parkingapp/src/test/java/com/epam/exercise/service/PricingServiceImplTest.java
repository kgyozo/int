package com.epam.exercise.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.exercise.config.Application;
import com.epam.exercise.entity.Company;
import com.epam.exercise.entity.PrivatePerson;
import com.epam.exercise.service_api.PricingService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PricingServiceImplTest {

    @Autowired
    PricingService pricingservice;

    @Test
    public void test_1WeekdayHourForCompany() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 15:49:39");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 16:49:39");
        Company company = new Company();
        Assert.assertEquals(0, pricingservice.calculateParkingFee(start1, end1, company));
    }

    @Test
    public void test_1WeekdayHourForPrivatePerson() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 15:49:39");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 16:49:39");
        PrivatePerson privatePerson = new PrivatePerson();
        Assert.assertEquals(0, pricingservice.calculateParkingFee(start1, end1, privatePerson));
    }

    @Test
    public void test_2WeekdayHoursForCompany() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 8:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 10:00:00");
        Company company = new Company();
        Assert.assertEquals(PricingService.COMPANY_HOUR_FEE, pricingservice.calculateParkingFee(start1, end1, company));
    }

    @Test
    public void test_2WeekdayHoursForPrivatePerson() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 8:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 10:00:00");
        PrivatePerson privatePerson = new PrivatePerson();
        Assert.assertEquals(PricingService.PRIVATE_HOUR_FEE,
                pricingservice.calculateParkingFee(start1, end1, privatePerson));
    }

    @Test
    public void test_1FullWeekdayForCompany() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 00:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 23:59:59");
        Company company = new Company();
        Assert.assertEquals(PricingService.COMPANY_HOUR_FEE * 23,
                pricingservice.calculateParkingFee(start1, end1, company));
    }

    @Test
    public void test_1FullWeekdayForPrivatePerson() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 00:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 23:59:59");
        PrivatePerson privatePerson = new PrivatePerson();
        Assert.assertEquals(PricingService.PRIVATE_HOUR_FEE * 23,
                pricingservice.calculateParkingFee(start1, end1, privatePerson));
    }

    @Test
    public void test_1WeekdayForCompany() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-21 08:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 08:00:00");
        Company company = new Company();
        Assert.assertEquals((15 + 8) * PricingService.COMPANY_HOUR_FEE,
                pricingservice.calculateParkingFee(start1, end1, company));
    }

    @Test
    public void test_1WeekdayForPrivatePerson() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-21 08:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-22 08:00:00");
        PrivatePerson privatePerson = new PrivatePerson();
        Assert.assertEquals((15 + 8) * PricingService.PRIVATE_HOUR_FEE,
                pricingservice.calculateParkingFee(start1, end1, privatePerson));
    }

    @Test
    public void test_3WeekdaysForCompany() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-21 08:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-24 08:00:00");
        Company company = new Company();
        Assert.assertEquals((15 + 24 + 24 + 8) * PricingService.COMPANY_HOUR_FEE,
                pricingservice.calculateParkingFee(start1, end1, company));
    }

    @Test
    public void test_3WeekdaysForPrivatePerson() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-21 08:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-24 08:00:00");
        PrivatePerson privatePerson = new PrivatePerson();
        Assert.assertEquals((15 + 24 + 24 + 8) * PricingService.PRIVATE_HOUR_FEE,
                pricingservice.calculateParkingFee(start1, end1, privatePerson));
    }

    @Test
    public void test_WeekendForCompany() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-25 08:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-26 11:00:00");
        Company company = new Company();
        Assert.assertEquals(2 * PricingService.COMPANY_DAY_FEE,
                pricingservice.calculateParkingFee(start1, end1, company));
    }

    @Test
    public void test_WeekendForPrivatePerson() throws ParseException {
        Date start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-25 08:00:00");
        Date end1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-04-26 11:00:00");
        PrivatePerson privatePerson = new PrivatePerson();
        Assert.assertEquals(2 * PricingService.PRIVATE_DAY_FEE,
                pricingservice.calculateParkingFee(start1, end1, privatePerson));
    }
}