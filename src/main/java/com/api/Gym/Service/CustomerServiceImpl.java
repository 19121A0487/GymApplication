package com.api.Gym.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Gym.DTO.CustomerDTO;
import com.api.Gym.Entity.Customer;
import com.api.Gym.Entity.Trainer;
import com.api.Gym.Repo.CustomerRepo;
import com.api.Gym.Repo.TrainerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepository;
    
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customers = mapToEntity(customerDTO);
        return mapToDTO(customerRepository.save(customers));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::mapToDTO).orElse(null);
    }

    @Override
     public CustomerDTO updateCustomerDetails(Long id, CustomerDTO customerDTO) {
        // 🔹 Fetch customer from database
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 🔹 Check if specialization is changing
        boolean specializationChanged = !existingCustomer.getSpecialization().equals(customerDTO.getSpecialization());

        // 🔹 Update customer fields
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        existingCustomer.setPhone(customerDTO.getPhone());
        existingCustomer.setWeight(customerDTO.getWeight());
        existingCustomer.setAge(customerDTO.getAge());
        existingCustomer.setHeight(customerDTO.getHeight());
        existingCustomer.setSpecialization(customerDTO.getSpecialization());  // ✅ Specialization updated

        // 🔹 If specialization changes, assign a new trainer
        if (specializationChanged) {
            Trainer assignedTrainer = findTrainerWithLeastCustomers(customerDTO.getSpecialization());
            existingCustomer.setTrainer(assignedTrainer);
        }

        // 🔹 Save updated customer
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return new CustomerDTO(updatedCustomer);
    }
    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public String determinePage(Integer age, Double weight, Double height, String specialization) {
        if (age == null || weight == null || height == null || specialization == null) {
            return "login";
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

        return "login";
    }

    @Override
    public CustomerDTO assignTrainerToCustomer(Long customerId, String specialization) {
        // Fetch the customer
    	Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Find the trainer with the least assigned customers
        Trainer leastAssignedTrainer = findTrainerWithLeastCustomers(specialization);

        // Assign the trainer to the customer
        customer.setTrainer(leastAssignedTrainer);

       
        customerRepository.save(customer);

        // Convert to DTO and return
        return new CustomerDTO(customer);
    }

//  Finds the trainer with the least assigned customers for a given specialization.
    private Trainer findTrainerWithLeastCustomers(String specialization) {
    	List<Trainer> trainers = trainerRepository.findBySpecialization(specialization);

        if (trainers.isEmpty()) {
            throw new RuntimeException("No trainers available for specialization: " + specialization);
        }

        return trainers.stream()
                .min(Comparator.comparingInt(trainer -> trainer.getCustomers().size()))
                .orElseThrow(() -> new RuntimeException("Error finding trainer"));
    }
    
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhone(customer.getPhone());
        dto.setAge(customer.getAge());
        dto.setWeight(customer.getWeight());
        dto.setHeight(customer.getHeight());
        dto.setSpecialization(customer.getSpecialization());
        
        // Add missing fields
        dto.setAddress(customer.getAddress()); // Fix: Set address
        dto.setGender(customer.getGender());   // Fix: Set gender
        dto.setTrainerName(customer.getTrainer() != null ? customer.getTrainer().getFirstName() : null); // Fix: Set trainer name
		return dto;

    }

    private Customer mapToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setAge(dto.getAge());
        customer.setWeight(dto.getWeight());
        customer.setHeight(dto.getHeight());
        customer.setSpecialization(dto.getSpecialization());

        // Add missing fields
        customer.setAddress(dto.getAddress()); // Fix: Set address
        customer.setGender(dto.getGender());   // Fix: Set gender

        if (dto.getId() != null) {
            customer.setTrainer(trainerRepository.findById(dto.getId()).orElse(null));
        }
        return customer;
    }

	
}
