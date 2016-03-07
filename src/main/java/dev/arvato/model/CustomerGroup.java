package dev.arvato.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerGroup {

	private static final long MSECONDS_IN_HALF_HOUR = 1800000;
	
	// NOTE: logic below using these values works well only if start hour and end hour both are > 1 && < 23, in other case have to adjust methods getDayEndTimeInMillis and getDayStartTimeInMillis
	private static final int DAY_START_HOUR = 7;
	private static final int DAY_END_HOUR = 19;

	public static final double REGULAR_DAY_COST = 1.50;
	public static final double REGULAR_EVENING_COST = 1.00;
	public static final double PREMIUM_DAY_COST = 1.00;
	public static final double PREMIUM_EVENING_COST = 0.75;

	public static final CustomerGroup REGULAR = new CustomerGroup(1L, "Regular", REGULAR_DAY_COST, REGULAR_EVENING_COST) {
		
		public String toString() { return "Regular"; }
		
	};
	public static final CustomerGroup PREMIUM = new CustomerGroup(2L, "Premium", PREMIUM_DAY_COST, PREMIUM_EVENING_COST) {
		
		public String toString() { return "Premium"; }
	};
	
	private long id;
	private String name;
	private double dayTimeCost;
	private double eveningTimeCost;
	
	public CustomerGroup() {
		id = 0;
	}
	
	public CustomerGroup(long id, String name, double dayTimeCost, double eveningTimeCost) {
		this.id = id;
		this.name = name;
		this.dayTimeCost = dayTimeCost;
		this.eveningTimeCost = eveningTimeCost;
	}
	
	public static List<CustomerGroup> getAllGroups() {
		return Arrays.asList(REGULAR, PREMIUM);
	}
	
	public double calculateParkingPrice(Date timeStarted, Date timeEnded) {
		return calculateParkingPrice(timeStarted, timeEnded, dayTimeCost, eveningTimeCost);
	}
	
//	public static CustomerGroup resolveGroup(String group) {
//		if(group == null) {
//			return REGULAR;
//		}
//		if(group.equalsIgnoreCase("premium")) {
//			return PREMIUM;
//		}
//		return REGULAR;
//	}
	
	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}
	
	public static double calculateParkingPrice(Date timeStarted, Date timeEnded, double dayCost, double eveningCost) {
		double price = 0.0;
		long timeStartedMs = timeStarted.getTime();
		long timeEndedMs = timeEnded.getTime();
		if(timeEndedMs <= timeStartedMs) {
			return price; // this should not occur -- still if end-time is same-or-less than start time no cost to be applied
		}
		for(long time = timeStartedMs; time < timeEndedMs; ) {
			long checkTime = time + MSECONDS_IN_HALF_HOUR;
			if(checkTime >= timeEndedMs) {
				checkTime = timeEndedMs;
			}
			boolean startAtDayTime = isDayTime(time);
			boolean endedAtDayTime = isDayTime(checkTime);
			
			price += startAtDayTime
					? dayCost
					: eveningCost;
			if(startAtDayTime != endedAtDayTime) { // occurred parking time cross [day -> evening] or [evening -> day]
				time = endedAtDayTime
					? getDayStartTimeInMillis(time)
					: getDayEndTimeInMillis(time);
			} else {
				time = checkTime;
			}
		}
		
		return price;
		
	}
	
	public static boolean isDayTime(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return hour >= DAY_START_HOUR && hour < DAY_END_HOUR;
	}
	
	public static long getDayEndTimeInMillis(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, DAY_END_HOUR);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	
	public static long getDayStartTimeInMillis(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, DAY_START_HOUR);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
}
