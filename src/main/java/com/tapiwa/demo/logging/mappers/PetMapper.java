package com.tapiwa.demo.logging.mappers;

import com.tapiwa.demo.logging.dto.PetDto;
import com.tapiwa.demo.logging.models.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {

   public PetDto modelToDto(Pet pet) {
        PetDto petDto = new PetDto();
        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        return petDto;
    }

    public Pet dtoToModel(PetDto petDto) {
        Pet pet = new Pet();
        pet.setId(petDto.getId());
        pet.setName(petDto.getName());
        return pet;
    }
}
