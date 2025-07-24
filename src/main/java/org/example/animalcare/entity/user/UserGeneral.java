package org.example.animalcare.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.animalcare.entity.pet.PetPost;
import org.example.animalcare.enums.UserType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
public abstract class UserGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    @Column(unique=true)
    private String email;
    private String phone;
    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PetPost> petPosts = new HashSet<>();



}
