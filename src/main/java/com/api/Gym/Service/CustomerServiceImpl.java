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
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setPhone(customerDetails.getPhone());
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
    
   

	@Override
	public String determinePage(Integer age, Double weight, Double height, String specialization) {
		// TODO Auto-generated method stub
		if (age == null || weight == null || height == null || specialization == null) {
            return "login"; // Default fallback
        }

        double bmi = weight / ((height / 100.0) * (height / 100.0));

        specialization = specialization.toLowerCase();
        String basePath = specialization.replace(" ", "-");

        if (age >= 18 && age <= 25) {
            if (bmi < 18.5) return basePath + "(18-25-underweight)";
            else if (bmi <= 24.9) return basePath + "(18-25-normal)";
            else return basePath + "(18-25-overweight)";
        } else if (age > 25 && age <= 40) {
            if (bmi < 18.5) return basePath + "(25-40-underweight)";
            else if (bmi <= 24.9) return basePath + "(25-40-normal)";
            else return basePath + "(25-40-overweight)";
        } else if (age > 40 && age <= 65) {
            if (bmi < 18.5) return basePath + "(40-65-underweight)";
            else if (bmi <= 24.9) return basePath + "(40-65-normal)";
            else return basePath + "(40-65-overweight)";
        }

        return "login"; // Fallback
	}

	
    
    

   
}
