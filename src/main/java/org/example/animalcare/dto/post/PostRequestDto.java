package org.example.animalcare.dto.post;

import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.animalcare.entity.user.UserGeneral;
import org.example.animalcare.enums.PostType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDto {
    private String title;
    private String description;
    private String animalType; // e.g., "Dog", "Cat", "Bird"
    private String breed;
    private String gender;
    private Integer age;
    private String color;
    private String healthCondition;
    private String location;
    private String photoUrl;

    private Long userId;
    private LocalDateTime createdAt;
    private PostType type;
}
