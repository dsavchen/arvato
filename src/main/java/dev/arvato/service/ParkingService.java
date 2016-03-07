package dev.arvato.service;

import dev.arvato.model.Customer;
import dev.arvato.model.ParkingInfo;
import dev.arvato.model.ParkingInvoice;

public interface ParkingService {
	

    void saveParkingInfo(ParkingInfo parkingInfo);
    
    ParkingInvoice getInvoice(Customer customer);

	
}
