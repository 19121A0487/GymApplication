package com.api.Gym.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Gym.Entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
