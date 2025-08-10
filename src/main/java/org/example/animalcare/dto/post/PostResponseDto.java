package org.example.animalcare.dto.post;

import lombok.*;
import org.example.animalcare.enums.PostType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
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

    private Long userId;
    private LocalDateTime createdAt;
    private PostType type;
}
