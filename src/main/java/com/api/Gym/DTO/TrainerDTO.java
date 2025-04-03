package com.api.Gym.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.api.Gym.Entity.Customer;
import com.api.Gym.Entity.Trainer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // ✅ Generates a no-argument constructor
public class TrainerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phone;
    private List<Long> customerIds;  // ✅ Added field for assigned customers

    // ✅ Constructor to Convert `Trainer` Entity → `TrainerDTO`
    public TrainerDTO(Trainer trainer) {
        this.id = trainer.getId();
        this.firstName = trainer.getFirstName();  // ✅ Correct field mapping
        this.lastName = trainer.getLastName();    // ✅ Correct field mapping
        this.specialization = trainer.getSpecialization();
        this.phone = trainer.getPhone();

        // ✅ Ensure customers are not null before mapping
        if (trainer.getCustomers() != null) {
            this.customerIds = trainer.getCustomers().stream()
                                      .map(Customer::getId)  // ✅ Maps only IDs
                                      .collect(Collectors.toList());
        }
    }
}
