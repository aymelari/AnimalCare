package org.example.animalcare.dto.user;

import lombok.*;
import org.example.animalcare.entity.pet.PetPost;
import org.example.animalcare.enums.UserType;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String username;
    private UserType userType;
    private String password;
    private String email;
    private String phone;
    private String shelterAddress;
    private Long cardDetails;
    private OffsetDateTime createdAt;
    private String pathToLicence;
    private Integer experience;
    private Set<Long> petPosts;
    private Boolean approved;
}
