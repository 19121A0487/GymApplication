package com.api.Gym.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Gym.Entity.Trainer;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Long>{
	
	List<Trainer> getTrainersBySpecialization(String specialization);

}
