package com.api.Gym.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Gym.Entity.Customer;
import com.api.Gym.Repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public Customer updateCustomerDetails(Long id, Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customer.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setAge(customerDetails.getAge());
            customer.setWeight(customerDetails.getWeight());
            customer.setHeight(customerDetails.getHeight());
            customer.setSpecialization(customerDetails.getSpecialization());

            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    // Determine which page to display based on the Customer's details
    public String determinePage(Customer customer) {
        double weight = customer.getWeight();
        double height = customer.getHeight();
        String specialization = customer.getSpecialization().toLowerCase();
        int age = customer.getAge(); // Assuming age is part of the Customer object

        if (weight == 65 && height == 172 && "body gain".equals(specialization)) {
            if (age >= 18 && age <= 25) {
                return "/views/body-gain-18-25.html";
            } else if (age > 25 && age <= 40) {
                return "/views/body-gain-25-40.html";
            } else if (age > 40 && age <= 65) {
                return "/views/body-gain-40-65.html";
            }
        } else if ("weight loss".equals(specialization)) {
            if (age >= 18 && age <= 25) {
                return "/views/weight-loss-18-25.html";
            } else if (age > 25 && age <= 40) {
                return "/views/weight-loss-25-40.html";
            } else if (age > 40 && age <= 65) {
                return "/views/weight-loss-40-65.html";
            }
        } else if ("fitness".equals(specialization)) {
            if (age >= 18 && age <= 25) {
                return "/views/fitness-18-25.html";
            } else if (age > 25 && age <= 40) {
                return "/views/fitness-25-40.html";
            } else if (age > 40 && age <= 65) {
                return "/views/fitness-40-60.html";
            }
        }

        // Fallback based on weight
        if (weight < 50) {
            return "/pages/underweight.html";
        } else if (weight > 80) {
            return "/pages/overweight.html";
        }

        return "/pages/general.html"; // Default page
    }
}
