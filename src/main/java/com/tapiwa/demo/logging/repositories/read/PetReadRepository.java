package com.tapiwa.demo.logging.repositories.read;

import com.tapiwa.demo.logging.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetReadRepository extends JpaRepository<Pet, Long> {
}
