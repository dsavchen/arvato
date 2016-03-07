package dev.arvato.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class ParkingServiceTest extends TestCase {

	public void testPriceCalculation() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = dateFormat.parse("2016-01-10 19:22");
//		assertTrue(ParkingService.);
		Date endDate = dateFormat.parse("2016-01-10 19:22");
	}
	
	
}
