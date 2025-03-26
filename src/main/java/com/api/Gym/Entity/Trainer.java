package com.api.Gym.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Trainer {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the trainer

    private String firstName;  
    private String lastName;   
    private String email;      
    private String phoneNumber; 
    private String gender;      
    private int age;
    private String specialization; 
    private Integer experienceYears;

	
	
	

}