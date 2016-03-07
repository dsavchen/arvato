package dev.arvato.service;

import java.util.List;

import dev.arvato.model.Customer;
 
public interface CustomerService {
     
    Customer findById(long id);
     
    void saveCustomer(Customer customer);
     
    void updateCustomer(Customer customer);
     
    void deleteCustomerById(long id);
 
    List<Customer> findAllCustomers(); 
     
    boolean isCustomerExist(Customer customer);     
}