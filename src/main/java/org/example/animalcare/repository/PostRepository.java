package org.example.animalcare.repository;

import org.example.animalcare.entity.pet.PetPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PetPost,Long> {
}
