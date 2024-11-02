package com.tapiwa.demo.logging.services;

import com.tapiwa.demo.logging.dto.PetDto;
import com.tapiwa.demo.logging.mappers.PetMapper;
import com.tapiwa.demo.logging.models.Pet;
import com.tapiwa.demo.logging.repositories.PetRepository;
import com.tapiwa.demo.logging.services.exceptions.PetServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;


    @Override
    public PetDto savePet(PetDto petDto) {
        Pet pet = petMapper.dtoToModel(petDto);
        pet = petRepository.save(pet);
        return petMapper.modelToDto(pet);
    }

    @Override
    @Cacheable(value = "petCache")
    public PetDto getPet(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetServiceException("Pet not found"));
        return petMapper.modelToDto(pet);
    }


    @Override
    public PetDto updatePet(PetDto petDto) {
        Pet pet = petRepository.findById(petDto.getId()).orElseThrow(() -> new PetServiceException("Pet not found"));
        pet = petMapper.dtoToModel(petDto);
        pet = petRepository.save(pet);
        return petMapper.modelToDto(pet);
    }


    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public List<PetDto> getAllPets() {

         return petRepository.findAll().stream().map(petMapper::modelToDto).collect(Collectors.toList());
    }
}