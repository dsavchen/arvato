package dev.arvato.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingInvoice {
	
	private List<ParkingCheck> checks = new ArrayList<ParkingCheck>();
	private double totalPrice;
	
	public ParkingInvoice() {
		/** noop */
	}
	
	public List<ParkingCheck> getChecks() {
		return checks;
	}
	
	public void setChecks(List<ParkingCheck> checks) {
		this.checks = checks;
	}
	
	public void addCheck(ParkingCheck check) {
		checks.add(check);
	}
	
	public void setTotalPrice(double price) {
		totalPrice = price;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
}
