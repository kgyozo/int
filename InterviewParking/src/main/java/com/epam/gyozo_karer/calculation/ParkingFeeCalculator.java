package com.epam.gyozo_karer.calculation;

import java.util.Date;

public interface ParkingFeeCalculator {
	public long calculateParkingFee(Date startDate, Date endDate);
}
