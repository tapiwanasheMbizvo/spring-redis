package com.tapiwa.demo.logging.repositories.write;

import com.tapiwa.demo.logging.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetWriteRepository extends JpaRepository<Pet, Long> {
}
