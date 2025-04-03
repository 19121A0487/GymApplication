package com.api.Gym.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.Gym.Entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
	
	List<Trainer> findBySpecialization(String specialization);
	
}
