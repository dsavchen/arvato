package dev.arvato.service;

import java.util.List;

import dev.arvato.model.Customer;
import dev.arvato.model.ParkingInfo;

public interface ParkingService {
	

    void saveParkingInfo(ParkingInfo parkingInfo);
    
    List<ParkingInfo> findAllParking(Customer customer); 

	
}
