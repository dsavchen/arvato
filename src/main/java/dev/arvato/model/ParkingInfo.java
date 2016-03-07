package dev.arvato.model;

public class ParkingInfo {
	
	private long id;
	private Customer customer;
	private String timeStarted;
	private String timeEnded;
	
	public ParkingInfo(){
        id=0;
    }
     
    public ParkingInfo(long id, Customer customer, String timeStarted, String timeEnded){
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
    	return timeStarted;
    }
    
    public void setTimeStarted(String timeStarted) {
    	this.timeStarted = timeStarted;
    }
    
    public String getTimeEnded() {
    	return timeEnded;
    }
    
    public void setTimeEnded(String timeEnded) {
    	this.timeEnded = timeEnded;
    }

}
