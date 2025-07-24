package org.example.animalcare.service;

import lombok.RequiredArgsConstructor;
import org.example.animalcare.dto.user.UserRequestDto;
import org.example.animalcare.entity.user.*;
import org.example.animalcare.enums.UserType;
import org.example.animalcare.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserGeneral createUser(UserRequestDto dto)
    {
        UserGeneral user;
        switch(dto.getUserType()){
            case NORMAL -> user = NormalUser.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .userType(UserType.NORMAL)
                    .build();
            case VETERINARIAN -> user= VeterinarianUser.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .userType(UserType.VETERINARIAN)
                    /*path logic */      .pathToLicence(dto.getPathToLicence())
                    .experience(dto.getExperience())
                    .approved(dto.isApproved())
                    .build();

            case SHELTER -> user= ShelterUser.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .userType(UserType.SHELTER)
                    .shelterAddress(dto.getShelterAddress())
                    .cardDetails(dto.getCardDetails())
                    .createdAt(dto.getCreatedAt())
                    .build();
            case ADMIN -> user= AdminUser.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .userType(UserType.ADMIN)
                    .build();

            default -> throw new IllegalArgumentException("Unknown user type: " + dto.getUserType());
        }

        return userRepository.save(user);




    }

}
