package com.tapiwa.demo.logging.services;

import com.tapiwa.demo.logging.dto.PetDto;

import java.util.List;

public interface PetService {

     PetDto savePet(PetDto petDto);
      PetDto getPet(Long id);

     PetDto updatePet(PetDto petDto);

     void deletePet(Long id);

     List<PetDto> getAllPets();
}
