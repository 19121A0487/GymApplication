package com.api.Gym.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Gym.Entity.Trainer;
import com.api.Gym.Service.TrainerService;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	
	@Autowired
	private TrainerService trainerService;
	
	@PostMapping("/add")
	public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
		
		Trainer addTrainer=trainerService.addTrainer(trainer);
		return new ResponseEntity<>(addTrainer, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/getTrainer/id/{id}")
	public ResponseEntity<Trainer> getTrainer(@PathVariable("id") Long id) {
	    Optional<Trainer> trainerById = trainerService.getTrainerById(id);
	    
	    if (trainerById.isPresent()) {
	        return new ResponseEntity<>(trainerById.get(), HttpStatus.OK);
	    } 
	    else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	
	@GetMapping("/getTrainer/{specialization}")
	public ResponseEntity<List<Trainer>> getTrainer2(@PathVariable String specialization) {
	    List<Trainer> trainerBySpecialization = trainerService.getTrainersBySpecialization(specialization);

	    if (!trainerBySpecialization.isEmpty()) {
	        return new ResponseEntity<>(trainerBySpecialization, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@PutMapping("/update/id/{id}")
	public ResponseEntity<?> updateTrainerDetails(@PathVariable Long id, @RequestBody Trainer trainer) {

//	     Try to find the restaurant by ID first
	    Optional<Trainer> existingTrainer = trainerService.getTrainerById(id);
 
	    if (existingTrainer.isPresent()) {
	        // Proceed with the update
	    	Trainer updatedTrainer = trainerService.updateTrainerDetails(id, trainer);
	        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK); // Return the updated restaurant
	    } else {
	        return new ResponseEntity<>("Trainer not found with id: " + id, HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRestaurentById(@PathVariable Long id){
		Optional<Trainer> trainer = trainerService.getTrainerById(id);
		if (trainer.isPresent()) {
	        // Call the delete method
	        trainerService.deleteTrainerById(id);
	        return new ResponseEntity<>(trainer.get(), HttpStatus.OK); // Return deleted restaurant with OK status
	    } else {
	        return new ResponseEntity<>("Trainer not found with id: " + id, HttpStatus.NOT_FOUND);
	    }
		
	}


}
