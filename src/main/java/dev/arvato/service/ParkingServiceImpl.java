package dev.arvato.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arvato.model.Customer;
import dev.arvato.model.CustomerGroup;
import dev.arvato.model.ParkingCheck;
import dev.arvato.model.ParkingInfo;
import dev.arvato.model.ParkingInvoice;
 
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
		List<ParkingInfo> res = new ArrayList<ParkingInfo>();
		if(customer == null) {
			return res;
		}
		for(ParkingInfo info: parking) {
			if(customer.equals(info.getCustomer())) {
				res.add(info);
			}
		}
		return res;
	}

	public ParkingInvoice getInvoice(Customer customer) {
		List<ParkingInfo> customerParking = findAllParking(customer);
		CustomerGroup group = customer.getGroup();
		double totalPrice = 0.0;
		ParkingInvoice ret = new ParkingInvoice();
		for(ParkingInfo info: customerParking) {
			double price = group.calculateParkingPrice(info.getTimeStartedDate(), info.getTimeEndedDate());
			ParkingCheck check = new ParkingCheck();
			check.setInfo(info);
			check.setPrice(price);
			totalPrice += price;

			ret.addCheck(check);
		}
		ret.setTotalPrice(totalPrice);
		return ret;
	}
 
}