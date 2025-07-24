package org.example.animalcare.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.animalcare.entity.pet.PetPost;
import org.example.animalcare.enums.UserType;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String shelterAddress;
    private Long cardDetails;
    private LocalDateTime createdAt;
    private String pathToLicence;
    private int experience;
    private UserType userType;
    private Set<Long> petPosts;
    private boolean approved;

}
