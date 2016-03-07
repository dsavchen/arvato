package dev.arvato.model;

import static dev.arvato.model.CustomerGroup.DATE_FORMAT;
import static dev.arvato.model.CustomerGroup.PREMIUM_DAY_COST;
import static dev.arvato.model.CustomerGroup.PREMIUM_EVENING_COST;
import static dev.arvato.model.CustomerGroup.REGULAR_DAY_COST;
import static dev.arvato.model.CustomerGroup.REGULAR_EVENING_COST;

import java.text.ParseException;
import java.util.Date;

import junit.framework.TestCase;

/** to unit-test algo methods in CustomerGroup */
public class CustomerGroupTest extends TestCase {

	public void testDayTime() throws ParseException {
		assertFalse(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 19:22").getTime()));
		assertFalse(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 19:00").getTime()));
		assertTrue(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 18:59").getTime()));
		assertTrue(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 7:59").getTime()));
		assertFalse(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 6:59").getTime()));
		
		// to check also times wrong by input (will be parsed to proper day-time)
		assertFalse(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 5:69").getTime()));
		assertTrue(CustomerGroup.isDayTime(DATE_FORMAT.parse("2016-01-10 6:69").getTime()));
	}
	
	public void testCalculateParkingPrice() throws ParseException {
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST, CustomerGroup.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 8:00"), DATE_FORMAT.parse("2016-01-10 9:00"), REGULAR_DAY_COST, REGULAR_EVENING_COST), 0.0);
		assertEquals(REGULAR_EVENING_COST + REGULAR_DAY_COST + REGULAR_DAY_COST, CustomerGroup.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 6:45"), DATE_FORMAT.parse("2016-01-10 7:45"), REGULAR_DAY_COST, REGULAR_EVENING_COST), 0.0);
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST + REGULAR_DAY_COST, CustomerGroup.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 16:51"), DATE_FORMAT.parse("2016-01-10 17:55"), REGULAR_DAY_COST, REGULAR_EVENING_COST), 0.0);
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST + REGULAR_EVENING_COST, CustomerGroup.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 18:11"), DATE_FORMAT.parse("2016-01-10 19:30"), REGULAR_DAY_COST, REGULAR_EVENING_COST), 0.0);
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST + REGULAR_EVENING_COST + REGULAR_EVENING_COST,  CustomerGroup.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 18:11"), DATE_FORMAT.parse("2016-01-10 19:31"), REGULAR_DAY_COST, REGULAR_EVENING_COST), 0.0);
	}

	
	public void testDayStartTimeCheck() throws ParseException {
		assertEquals("2016-01-10 19:00", DATE_FORMAT.format(new Date(CustomerGroup.getDayEndTimeInMillis(DATE_FORMAT.parse("2016-01-10 23:22").getTime()))));
		assertEquals("2016-01-10 19:00", DATE_FORMAT.format(new Date(CustomerGroup.getDayEndTimeInMillis(DATE_FORMAT.parse("2016-01-10 18:11").getTime()))));
		assertEquals("2016-01-10 07:00", DATE_FORMAT.format(new Date(CustomerGroup.getDayStartTimeInMillis(DATE_FORMAT.parse("2016-01-10 6:01").getTime()))));
		assertEquals("2016-01-10 07:00", DATE_FORMAT.format(new Date(CustomerGroup.getDayStartTimeInMillis(DATE_FORMAT.parse("2016-01-10 7:31").getTime()))));
		
	}
	
	public void testCustomerParkingPrice() throws ParseException {
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST, CustomerGroup.REGULAR.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 8:00"), DATE_FORMAT.parse("2016-01-10 9:00")), 0.0);
		assertEquals(REGULAR_EVENING_COST + REGULAR_DAY_COST + REGULAR_DAY_COST, CustomerGroup.REGULAR.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 6:45"), DATE_FORMAT.parse("2016-01-10 7:45")), 0.0);
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST + REGULAR_DAY_COST, CustomerGroup.REGULAR.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 16:51"), DATE_FORMAT.parse("2016-01-10 17:55")), 0.0);
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST + REGULAR_EVENING_COST, CustomerGroup.REGULAR.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 18:11"), DATE_FORMAT.parse("2016-01-10 19:30")), 0.0);
		assertEquals(REGULAR_DAY_COST + REGULAR_DAY_COST + REGULAR_EVENING_COST + REGULAR_EVENING_COST,  CustomerGroup.REGULAR.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 18:11"), DATE_FORMAT.parse("2016-01-10 19:31")), 0.0);
		
		assertEquals(PREMIUM_DAY_COST + PREMIUM_DAY_COST, CustomerGroup.PREMIUM.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 8:00"), DATE_FORMAT.parse("2016-01-10 9:00")), 0.0);
		assertEquals(PREMIUM_EVENING_COST + PREMIUM_DAY_COST + PREMIUM_DAY_COST, CustomerGroup.PREMIUM.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 6:45"), DATE_FORMAT.parse("2016-01-10 7:45")), 0.0);
		assertEquals(PREMIUM_DAY_COST + PREMIUM_DAY_COST + PREMIUM_DAY_COST, CustomerGroup.PREMIUM.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 16:51"), DATE_FORMAT.parse("2016-01-10 17:55")), 0.0);
		assertEquals(PREMIUM_DAY_COST + PREMIUM_DAY_COST + PREMIUM_EVENING_COST, CustomerGroup.PREMIUM.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 18:11"), DATE_FORMAT.parse("2016-01-10 19:30")), 0.0);
		assertEquals(PREMIUM_DAY_COST + PREMIUM_DAY_COST + PREMIUM_EVENING_COST + PREMIUM_EVENING_COST,  CustomerGroup.PREMIUM.calculateParkingPrice(DATE_FORMAT.parse("2016-01-10 18:11"), DATE_FORMAT.parse("2016-01-10 19:31")), 0.0);

	}

	
}
