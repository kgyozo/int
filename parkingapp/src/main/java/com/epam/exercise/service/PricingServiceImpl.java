package com.epam.exercise.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.epam.exercise.entity.Company;
import com.epam.exercise.entity.Customer;
import com.epam.exercise.entity.PrivatePerson;
import com.epam.exercise.service_api.PricingService;

@Service
public class PricingServiceImpl implements PricingService {

    public long calculateParkingFee(Date start, Date end, Customer customer) {

        int sumWorkdayHours = getFullWorkdayHours(start, end) + getPricedPartialWorkDayHours(start, end);
        int sumWeekendDays = getWeekendDays(start, end);

        long result = 0;

        if (customer instanceof Company) {
            result = sumWorkdayHours * PricingService.COMPANY_HOUR_FEE + sumWeekendDays
                    * PricingService.COMPANY_DAY_FEE;
        }
        if (customer instanceof PrivatePerson) {
            result = sumWorkdayHours * PricingService.PRIVATE_HOUR_FEE + sumWeekendDays
                    * PricingService.PRIVATE_DAY_FEE;
        }

        return result;
    }

    /**
     * Returns only the number of weekend days.
     */
    private int getWeekendDays(Date start, Date end) {
        
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if (startCalendar.getTimeInMillis() == endCalendar.getTimeInMillis())
            return 0;

        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 1);
        endCalendar.set(Calendar.MILLISECOND, 0);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 1);
        startCalendar.set(Calendar.MILLISECOND, 0);

        int result = 0;

        while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
            if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                result++;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return result;
    }

    /**
     * Returns only the partial workday hours.
     */
    private int getPricedPartialWorkDayHours(Date start, Date end) {

        int result = 0;

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if (startCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR)
                && startCalendar.get(Calendar.DAY_OF_YEAR) == endCalendar.get(Calendar.DAY_OF_YEAR)
                && startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                && startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

            long timeInMillis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
            double minutes = timeInMillis / 1000 / 60;
            int hours = (int) (minutes % 60 == 0 ? minutes / 60 : minutes / 60 + 1);
            if (hours > 0) {
                result = result + hours - 1;
            }
        } else {

            if (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

                Calendar midnight = (Calendar) startCalendar.clone();
                midnight.add(Calendar.DAY_OF_WEEK, 1);
                midnight.set(Calendar.HOUR_OF_DAY, 0);
                midnight.set(Calendar.MINUTE, 0);
                midnight.set(Calendar.SECOND, 0);
                midnight.set(Calendar.MILLISECOND, 0);

                long timeInMillis = midnight.getTimeInMillis() - startCalendar.getTimeInMillis();
                double minutes = timeInMillis / 1000 / 60;
                int hours = (int) (minutes % 60 == 0 ? minutes / 60 : minutes / 60 + 1);
                if (hours > 0) {
                    result = result + hours - 1;
                }
            }

            if (endCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && endCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

                Calendar midnight = (Calendar) endCalendar.clone();
                midnight.set(Calendar.HOUR_OF_DAY, 0);
                midnight.set(Calendar.MINUTE, 0);
                midnight.set(Calendar.SECOND, 0);
                midnight.set(Calendar.MILLISECOND, 0);

                long timeInMillis = endCalendar.getTimeInMillis() - midnight.getTimeInMillis();
                double minutes = timeInMillis / 1000 / 60;
                int hours = (int) (minutes % 60 == 0 ? minutes / 60 : minutes / 60 + 1);
                if (hours > 0) {
                    result = result + hours;
                }
            }
        }

        return result;

    }

    /**
     * Returns only the full workday hours. Partial workdays does not count.
     */
    private int getFullWorkdayHours(Date start, Date end) {
        
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        if (startCalendar.getTimeInMillis() == endCalendar.getTimeInMillis())
            return 0;

        endCalendar.add(Calendar.DAY_OF_WEEK, -1);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 1);
        endCalendar.set(Calendar.MILLISECOND, 0);
        startCalendar.add(Calendar.DAY_OF_WEEK, 1);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 1);
        startCalendar.set(Calendar.MILLISECOND, 0);

        int result = 0;

        while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
            if (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                result++;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return result * 24;
    }
}
