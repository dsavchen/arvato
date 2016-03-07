package dev.arvato.model;

import java.text.ParseException;
import java.util.Date;

public class ParkingInfo {
	
	private long id;
	private Customer customer;
	private Date timeStarted;
	private Date timeEnded;
	
	public ParkingInfo(){
        id=0;
    }
     
    public ParkingInfo(long id, Customer customer, Date timeStarted, Date timeEnded){
        this.id = id;
        this.customer = customer;
        this.timeStarted = timeStarted;
        this.timeEnded = timeEnded;
    }
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public Customer getCustomer() {
    	return customer;
    }
    
    public void setCustomer(Customer customer) {
    	this.customer = customer;
    }
    
    public String getTimeStarted() {
    	return CustomerGroup.DATE_FORMAT.format(timeStarted);
    }
 
    public Date getTimeStartedDate() {
    	return timeStarted;
    }
    
    public void setTimeStarted(String timeStarted) throws ParseException {
    	this.timeStarted = CustomerGroup.DATE_FORMAT.parse(timeStarted);
    }
    
    public String getTimeEnded() {
    	return CustomerGroup.DATE_FORMAT.format(timeEnded);
    }
    
    public Date getTimeEndedDate() {
    	return timeEnded;
    }
    
    public void setTimeEnded(String timeEnded) throws ParseException {
    	this.timeEnded = CustomerGroup.DATE_FORMAT.parse(timeEnded);
    }

}
