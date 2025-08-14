package org.example.animalcare.dto.post;

import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.animalcare.entity.user.UserGeneral;
import org.example.animalcare.enums.PostType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDto {

    private Long userId;
    private String title;
    private String description;
    private String animalType; // e.g., "Dog", "Cat", "Bird"
    private String breed;
    private String gender;
    private Integer age;
    private String color;
    private String healthCondition;
    private String location;
    private LocalDateTime createdAt;
   /* private String photoKey;*/
    private PostType type;
}
