package org.example.animalcare.repository;

import org.example.animalcare.entity.pet.PetPost;
import org.example.animalcare.entity.user.UserGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<PetPost,Long> {
    Set<PetPost> findByUser(UserGeneral user);
}
