package dev.arvato.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arvato.model.Customer;
import dev.arvato.model.ParkingInfo;
 
@Service("parkingService")
@Transactional
public class ParkingServiceImpl implements ParkingService {
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<ParkingInfo> parking = new ArrayList<ParkingInfo>();
     
     
	public void saveParkingInfo(ParkingInfo parkingInfo) {
		parkingInfo.setId(counter.incrementAndGet());
        parking.add(parkingInfo);
		
	}

	public List<ParkingInfo> findAllParking(Customer customer) {
		return parking;
	}
 
}