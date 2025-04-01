package com.api.Gym.Service;

import java.util.List;
import com.api.Gym.Entity.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomerDetails(Long id, Customer customer);
    void deleteById(Long id);
    String determinePage(Customer customer);
}
