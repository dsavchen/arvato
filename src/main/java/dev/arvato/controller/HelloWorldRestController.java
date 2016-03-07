package dev.arvato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
import dev.arvato.model.Customer;
import dev.arvato.model.CustomerGroup;
import dev.arvato.model.ParkingInfo;
import dev.arvato.service.CustomerService;
import dev.arvato.service.ParkingService;
  
@RestController
public class HelloWorldRestController {
  
    @Autowired
    CustomerService customerService;  //does all data retrieval/manipulation work
    @Autowired
    ParkingService parkingService;  //does all data retrieval/manipulation work
  
     
    //-------------------Retrieve All Customers -----------------------------------------
    
    @RequestMapping(value = "/parking/", method = RequestMethod.GET)
    public ResponseEntity<List<ParkingInfo>> listAllParking(@RequestBody Customer customer) {
        List<ParkingInfo> parkingInfo = parkingService.findAllParking(customer);
    	System.out.println("list all parking -- "+parkingInfo.size());
        if(parkingInfo.isEmpty()){
            return new ResponseEntity<List<ParkingInfo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ParkingInfo>>(parkingInfo, HttpStatus.OK);
    }
  
    //-------------------Create a Customer -----------------------------------------
      
    @RequestMapping(value = "/parking/", method = RequestMethod.POST)
    public ResponseEntity<Void> createParking(@RequestBody ParkingInfo parkingInfo, UriComponentsBuilder ucBuilder) {
  
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Create parking "+parkingInfo.getId()+" "+parkingInfo.getTimeEnded()+" "+parkingInfo.getTimeStarted()+" "+parkingInfo.getCustomer());
//        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    
    //-------------------Retrieve All Customers -----------------------------------------
      
    @RequestMapping(value = "/customer/", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
    	System.out.println("list all customers -- "+customers.size());
        if(customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
  
    @RequestMapping(value = "/groups/", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerGroup>> listAllGroups() {
        List<CustomerGroup> res = CustomerGroup.getAllGroups();
        return new ResponseEntity<List<CustomerGroup>>(res, HttpStatus.OK);
    }
     
    //-------------------Retrieve Single Customer ---------------------------------------
      
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
      
    //-------------------Create a Customer -----------------------------------------
      
    @RequestMapping(value = "/customer/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
  
    	if (customerService.isCustomerExist(customer)) {
            System.out.println("A Customer with name " + customer.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    	System.out.println("Create customer -- "+customer.getName());
    	customer.setGroup(customer.getGroup());
        customerService.saveCustomer(customer);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
      
    //------------------- Update a Customer --------------------------------------------------------
      
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        Customer currentCustomer = customerService.findById(id);
          
        if (currentCustomer==null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
  
        currentCustomer.setName(customer.getName());
        currentCustomer.setGroup(customer.getGroup());
          
        customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
    }
  
     
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Customer with id " + id);
  
        Customer customer = customerService.findById(id);
        if (customer == null) {
            System.out.println("Unable to delete. Customer with id " + id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
  
        customerService.deleteCustomerById(id);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
  
  
}