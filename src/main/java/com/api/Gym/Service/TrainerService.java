package com.api.Gym.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Gym.DTO.TrainerDTO;
import com.api.Gym.Entity.Trainer;
import com.api.Gym.Repo.TrainerRepository;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public List<TrainerDTO> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TrainerDTO> getTrainerById(Long id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        return trainer.map(this::convertToDTO);
    }

    public TrainerDTO saveTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = convertToEntity(trainerDTO);
        Trainer savedTrainer = trainerRepository.save(trainer);
        return convertToDTO(savedTrainer);
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    public TrainerDTO updateTrainerDetails(Long id, TrainerDTO trainerDTO) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(id);
        if (optionalTrainer.isPresent()) {
            Trainer trainer = optionalTrainer.get();
            trainer.setFirstName(trainerDTO.getFirstName());
            trainer.setLastName(trainerDTO.getLastName());
            trainer.setPhone(trainerDTO.getPhone());
            trainer.setSpecialization(trainerDTO.getSpecialization());

            Trainer updatedTrainer = trainerRepository.save(trainer);
            return convertToDTO(updatedTrainer);
        }
        return null;
    }

    private TrainerDTO convertToDTO(Trainer trainer) {
        TrainerDTO dto = new TrainerDTO();
        dto.setId(trainer.getId());
        dto.setFirstName(trainer.getFirstName());
        dto.setLastName(trainer.getLastName());
        dto.setSpecialization(trainer.getSpecialization());
        dto.setPhone(trainer.getPhone());

        if (trainer.getCustomers() != null) {
            dto.setCustomerIds(trainer.getCustomers().stream().map(c -> c.getId()).collect(Collectors.toList()));
        }

        return dto;
    }

    private Trainer convertToEntity(TrainerDTO dto) {
        Trainer trainer = new Trainer();
        trainer.setId(dto.getId());
        trainer.setFirstName(dto.getFirstName());
        trainer.setLastName(dto.getLastName());
        trainer.setSpecialization(dto.getSpecialization());
        trainer.setPhone(dto.getPhone());

        return trainer;
    }
}
