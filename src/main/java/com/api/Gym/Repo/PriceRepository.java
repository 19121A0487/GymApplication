package com.api.Gym.Repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.Gym.Entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
	
	Optional<Price> findByCustomerId(Long customerId);

}
