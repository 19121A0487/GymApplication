package com.api.Gym.DTO;



import org.aspectj.apache.bcel.classfile.NestHost;

import com.api.Gym.Entity.Customer;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Address is required")
    @Size(min = 5, message = "Address must be at least 5 characters long")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @DecimalMin(value = "30.0", message = "Weight must be at least 30 kg")
    @DecimalMax(value = "200.0", message = "Weight cannot be more than 200 kg")
    private Double weight;  

    @NotBlank(message = "Gender is required")
    private String gender;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot be more than 100")
    private int age;

    @Min(value = 50, message = "Height must be at least 50 cm")
    @Max(value = 250, message = "Height cannot be more than 250 cm")
    private double height;

    @NotBlank(message = "Specialization is required")
    @Size(min = 3, message = "Specialization must be at least 3 characters")
    private String specialization;

    
   
    private String trainerName;
    
    @NotBlank(message = "Training period is required")
    private int trainingPeriod;
    
    private Double basePrice;

    private String couponCode;

    private Double finalPrice;
    
    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
        this.weight = customer.getWeight();
        this.gender = customer.getGender();
        this.age = customer.getAge();
        this.height = customer.getHeight();
        this.specialization = customer.getSpecialization();
        this.trainingPeriod=customer.getTrainingPeriod();
        this.basePrice=customer.getBasePrice();
        this.couponCode=customer.getCouponCode();
        this.finalPrice=customer.getFinalPrice();
        
        
        // ✅ Handle null trainer case
        this.trainerName = (customer.getTrainer() != null) ? customer.getTrainer().getFirstName() : "Not Assigned";
    }
    public CustomerDTO() {
    	
    }
}


