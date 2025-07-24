package org.example.animalcare.repository;

import org.example.animalcare.entity.user.UserGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserGeneral, Long> {

}
