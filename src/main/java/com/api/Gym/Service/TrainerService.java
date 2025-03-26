package com.api.Gym.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Gym.Entity.Trainer;
import com.api.Gym.Repo.TrainerRepo;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepo trainerRepo;

    // Add a new trainer
    public Trainer addTrainer(Trainer trainer) {
        return trainerRepo.save(trainer);
    }

    // Get all trainers
    public List<Trainer> getAllTrainers() {
        return trainerRepo.findAll();
    }

    // Get a trainer by ID
    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepo.findById(id);
    }

    // Get trainers by specialization
    public List<Trainer> getTrainersBySpecialization(String specialization) {
        return trainerRepo.getTrainersBySpecialization(specialization);
    }

    // Update trainer details
    public Trainer updateTrainerDetails(Long id, Trainer trainer) {
        Trainer updateTrainer = trainerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found with id: " + id));

        // Update only the fields that are not null in the incoming Trainer object
        if (trainer.getFirstName() != null) {
            updateTrainer.setFirstName(trainer.getFirstName());
        }
        if (trainer.getLastName() != null) {
            updateTrainer.setLastName(trainer.getLastName());
        }
        if (trainer.getEmail() != null) {
            updateTrainer.setEmail(trainer.getEmail());
        }
        if (trainer.getPhoneNumber() != null) {
            updateTrainer.setPhoneNumber(trainer.getPhoneNumber());
        }
        if (trainer.getGender() != null) {
            updateTrainer.setGender(trainer.getGender());
        }
        if (trainer.getAge() != 0) {  // Age is an int, so check if it's not the default value
            updateTrainer.setAge(trainer.getAge());
        }
        if (trainer.getSpecialization() != null) {
            updateTrainer.setSpecialization(trainer.getSpecialization());
        }
        if (trainer.getExperienceYears() != null) {
            updateTrainer.setExperienceYears(trainer.getExperienceYears());
        }

        return trainerRepo.save(updateTrainer);
    }
    
	public Trainer deleteTrainerById(Long id) {
			
		trainerRepo.deleteById(id);
		return null;
		
	}
}
