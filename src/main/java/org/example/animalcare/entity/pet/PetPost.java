package org.example.animalcare.entity.pet;

import jakarta.persistence.*;
import lombok.*;
import org.example.animalcare.entity.user.UserGeneral;
import org.example.animalcare.enums.PostType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String animalType; // e.g., "Dog", "Cat", "Bird"
    private String breed;
    private String gender;
    private Integer age;
    private String color;
    private String healthCondition;
    private String location;

    private String photoPath;

    @ManyToOne
    private UserGeneral user;
    private LocalDateTime createdAt;
    private PostType type;
}