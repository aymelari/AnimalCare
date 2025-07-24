package org.example.animalcare.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("VETERINARIAN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VeterinarianUser extends UserGeneral {
        private String pathToLicence;
        private int experience;
        private boolean approved;
}
