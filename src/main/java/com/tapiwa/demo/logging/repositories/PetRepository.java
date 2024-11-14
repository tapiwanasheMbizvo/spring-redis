package com.tapiwa.demo.logging.repositories;

import com.tapiwa.demo.logging.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository  extends JpaRepository<Pet, Long> {

    List<Pet> findByOwnerId(String ownerId);
}
