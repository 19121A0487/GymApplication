package com.api.Gym.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Gym.Entity.Customer;
import com.api.Gym.Entity.Trainer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
	

}
