package com.api.Gym.Service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.Gym.DTO.CustomerDTO;
import com.api.Gym.Entity.Trainer;
import com.api.Gym.Repo.TrainerRepository;

public interface CustomerService {
	
	
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO updateCustomerDetails(Long id, CustomerDTO customerDTO);
    void deleteById(Long id);
    String determinePage(Integer age, Double weight, Double height, String specialization);
    CustomerDTO assignTrainerToCustomer(Long customerId, String specialization);
//    public Double calculatePrice(String trainingPeriod, String couponCode);
    

}
