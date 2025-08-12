package org.example.animalcare.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.animalcare.enums.UserType;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String shelterAddress;
    private Long cardDetails;
    private OffsetDateTime createdAt;
    private String pathToLicence;
    private int experience;
    private UserType userType;
    private Set<Long> petPosts;
    private boolean approved;
}
