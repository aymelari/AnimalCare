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
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShelterUser extends UserGeneral {
    private String shelterAddress;
    private Long cardDetails;
    private LocalDateTime createdAt;

}
