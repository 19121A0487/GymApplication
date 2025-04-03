package com.api.Gym.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Gym.DTO.TrainerDTO;
import com.api.Gym.Service.TrainerService;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/get")
    public List<TrainerDTO> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/get/{id}")
    public Optional<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return trainerService.getTrainerById(id);
    }

    @PostMapping("/add")
    public TrainerDTO addTrainer(@RequestBody TrainerDTO trainer) {
        return trainerService.saveTrainer(trainer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<TrainerDTO>  updateTrainerDetails(@PathVariable Long id,@RequestBody TrainerDTO trainer) {
    	TrainerDTO updatedTrainer = trainerService.updateTrainerDetails(id, trainer);
        return ResponseEntity.ok(updatedTrainer);
    }
    
}
