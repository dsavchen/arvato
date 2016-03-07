package dev.arvato.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import dev.arvato.model.Customer;
import dev.arvato.model.CustomerGroup;
 
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<Customer> customers;
     
    static{
        customers = populateDummyCustomers();
    }
 
    public List<Customer> findAllCustomers() {
        return customers;
    }
     
    public Customer findById(long id) {
        for(Customer customer : customers){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }
     
    public void saveCustomer(Customer customer) {
        customer.setId(counter.incrementAndGet());
        customers.add(customer);
    }
 
    public void updateCustomer(Customer customer) {
        int index = customers.indexOf(customer);
        customers.set(index, customer);
    }

    public boolean isCustomerExist(Customer customer) {
        return findByName(customer.getName())!=null;
    }
    
    public Customer findByName(String name) {
    	if(name == null) {
    		return null;
    	}
        for(Customer customer : customers){
            if(customer.getName().equalsIgnoreCase(name)){
                return customer;
            }
        }
        return null;
    }
     
    
    public void deleteCustomerById(long id) {
         
        for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); ) {
            Customer customer = iterator.next();
            if (customer.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    private static List<Customer> populateDummyCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer(counter.incrementAndGet(), "Bill", CustomerGroup.REGULAR));
        customers.add(new Customer(counter.incrementAndGet(), "Nick", CustomerGroup.REGULAR));
        customers.add(new Customer(counter.incrementAndGet(), "Bob", CustomerGroup.PREMIUM));
        return customers;
    }
 
}