package com.api.Gym.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.api.Gym.DTO.CustomerDTO;
import com.api.Gym.Service.CustomerService;
//@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


    // Get all Customers
    @GetMapping("/get")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Get a Customer by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // Update a Customer by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        CustomerDTO updatedCustomer = customerService.updateCustomerDetails(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Delete a Customer by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    

    
    @GetMapping("/determine-page")
    public ModelAndView determinePage(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "weight", required = false) Double weight,
            @RequestParam(value = "height", required = false) Double height,
            @RequestParam(value = "specialization", required = false) String specialization) {

        System.out.println("Received details - Age: " + age + ", Weight: " + weight + ", Height: " + height + ", Specialization: " + specialization);

        String viewName = customerService.determinePage(age, weight, height, specialization);
        
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("age", age);
        modelAndView.addObject("weight", weight);
        modelAndView.addObject("height", height);
        modelAndView.addObject("specialization", specialization);

        return modelAndView;
    }
    
    @PutMapping("/{customerId}/assign-trainer/{specialization}")
    public CustomerDTO assignTrainer(@PathVariable Long customerId, @PathVariable String specialization) {
        return customerService.assignTrainerToCustomer(customerId, specialization);
    }
    
    @GetMapping("/some-endpoint/{id}")
    public ResponseEntity<?> someMethod(@PathVariable String id) {
        System.out.println("Received id: " + id);
        // method logic here
        return ResponseEntity.ok("ID received: " + id);
    }


}
