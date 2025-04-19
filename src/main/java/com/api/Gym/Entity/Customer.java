package com.api.Gym.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
@Table(name="customers")
public class Customer {
	
	
	 @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
	 
	 public Trainer getTrainer() {
	        return trainer;
	    }

	    public void setTrainer(Trainer trainer) {
	        this.trainer = trainer;
	    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Double weight;  // Use `Double` instead of `double` to avoid validation issues

    @NotBlank(message = "Gender is required")
//    @Pattern(regexp = "^(Male|Female|Not to be)$", message = "Gender must be either 'Male', 'Female', or 'Not to be'")
    private String gender;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot be more than 100")
    private int age;

    @Min(value = 50, message = "Height must be at least 50 cm")
    @Max(value = 200, message = "Height cannot be more than 250 cm")
    private double height;

    @NotBlank(message = "Specialization is required")
    @Size(min = 3, message = "Specialization must be at least 3 characters")
    private String specialization;
    
    
    
    @NotBlank(message = "Training period is required")
    private int trainingPeriod;
    
    

    
    
    // Getters and Setters
}