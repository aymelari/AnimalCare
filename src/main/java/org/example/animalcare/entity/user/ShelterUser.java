package org.example.animalcare.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("SHELTER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShelterUser extends UserGeneral {
    private String shelterAddress;
    private Long cardDetails;
    private LocalDateTime createdAt;

}
