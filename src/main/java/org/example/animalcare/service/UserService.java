package org.example.animalcare.service;

import lombok.RequiredArgsConstructor;
import org.example.animalcare.dto.user.UserRequestDto;
import org.example.animalcare.dto.user.UserResponseDto;
import org.example.animalcare.entity.pet.PetPost;
import org.example.animalcare.entity.user.*;
import org.example.animalcare.enums.UserType;
import org.example.animalcare.exception.UserNotFoundException;
import org.example.animalcare.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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


     public UserResponseDto getUserById(Long id)
     {
         UserGeneral userGeneral = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found" + id));
         Set<PetPost> petPosts = userGeneral.getPetPosts();
         Set<Long> collect = petPosts.stream().map(post -> post.getId()).collect(Collectors.toSet());

         UserResponseDto userResponse = UserResponseDto.builder()
                 .id(userGeneral.getId())
                 .username(userGeneral.getUsername())
                 .email(userGeneral.getEmail())
                 .phone(userGeneral.getPhone())
                 .userType(userGeneral.getUserType())
                 .petPosts(collect)
                 .build();


         switch(userGeneral.getUserType()){
             case VETERINARIAN -> {
                 VeterinarianUser veterinarianUser=  (VeterinarianUser) userGeneral;
                 userResponse.setPathToLicence(veterinarianUser.getPathToLicence());
                 userResponse.setExperience(veterinarianUser.getExperience());
                 userResponse.setApproved(veterinarianUser.isApproved());
             }
             case SHELTER -> {
                 ShelterUser shelterUser= (ShelterUser) userGeneral;
                 userResponse.setShelterAddress(shelterUser.getShelterAddress());
                 userResponse.setCardDetails(shelterUser.getCardDetails());
                 userResponse.setCreatedAt(shelterUser.getCreatedAt());
             }
         }
         return userResponse;

     }

     public ArrayList<UserResponseDto> getAllUsers(){
         List<UserGeneral> allUser = userRepository.findAll();
         ArrayList<UserResponseDto> userResponseDtos = new ArrayList<>();
         for(UserGeneral userGeneral: allUser){
             Set<PetPost> petPosts = userGeneral.getPetPosts();
             Set<Long> collect = petPosts.stream().map(post -> post.getId()).collect(Collectors.toSet());

             UserResponseDto userResponse = UserResponseDto.builder()
                     .id(userGeneral.getId())
                     .username(userGeneral.getUsername())
                     .email(userGeneral.getEmail())
                     .phone(userGeneral.getPhone())
                     .userType(userGeneral.getUserType())
                     .petPosts(collect)
                     .build();


             switch(userGeneral.getUserType()){
                 case VETERINARIAN -> {
                     VeterinarianUser veterinarianUser=  (VeterinarianUser) userGeneral;
                     userResponse.setPathToLicence(veterinarianUser.getPathToLicence());
                     userResponse.setExperience(veterinarianUser.getExperience());
                     userResponse.setApproved(veterinarianUser.isApproved());
                 }
                 case SHELTER -> {
                     ShelterUser shelterUser= (ShelterUser) userGeneral;
                     userResponse.setShelterAddress(shelterUser.getShelterAddress());
                     userResponse.setCardDetails(shelterUser.getCardDetails());
                     userResponse.setCreatedAt(shelterUser.getCreatedAt());
                 }
             }
             userResponseDtos.add(userResponse);
         }

           return userResponseDtos;
     }



    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found" + id));
        userRepository.deleteById(id);
    }

    public UserResponseDto updateUserFields(UserRequestDto dto, Long id){
        Optional<UserGeneral> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("user not found" + id);
        }else{
            UserGeneral userGeneral = user.get();
            userGeneral.setUsername(dto.getUsername());
            userGeneral.setPassword(dto.getPassword());
            userGeneral.setEmail(dto.getEmail());
            userGeneral.setPhone(dto.getPhone());
            userGeneral.setUserType(dto.getUserType());
            switch(userGeneral.getUserType()){
                case VETERINARIAN -> {
                    VeterinarianUser veterinarianUser=  (VeterinarianUser) userGeneral;
                    veterinarianUser.setPathToLicence(dto.getPathToLicence());
                    veterinarianUser.setExperience(dto.getExperience());
                    veterinarianUser.setApproved(dto.isApproved());
                }
                case SHELTER -> {
                    ShelterUser shelterUser= (ShelterUser) userGeneral;
                    shelterUser.setShelterAddress(dto.getShelterAddress());
                    shelterUser.setCardDetails(dto.getCardDetails());
                    shelterUser.setCreatedAt(dto.getCreatedAt());
                }
            }
           userRepository.save(userGeneral);
        }
    return getUserById(id);
    }






}





