package dev.arvato.model;

public class ParkingCheck {
	
	private ParkingInfo info;
	private double price;
	
	public ParkingCheck() {
		/** noop **/
	}
	
	public ParkingInfo getInfo() {
		return info;
	}
	
	public void setInfo(ParkingInfo info) {
		this.info = info;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
