package com.tapiwa.demo.logging.services;

import com.tapiwa.demo.logging.dto.PetDto;
import com.tapiwa.demo.logging.mappers.PetMapper;
import com.tapiwa.demo.logging.models.Pet;
import com.tapiwa.demo.logging.repositories.PetRepository;
import com.tapiwa.demo.logging.services.exceptions.PetServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetMapper petMapper;

    @InjectMocks
    private PetServiceImpl petServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePet() {
        PetDto petDto = new PetDto();
        Pet pet = new Pet();
        when(petMapper.dtoToModel(petDto)).thenReturn(pet);
        when(petRepository.save(pet)).thenReturn(pet);
        when(petMapper.modelToDto(pet)).thenReturn(petDto);

        PetDto result = petServiceImpl.savePet(petDto);

        assertNotNull(result);
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void getPet() {
        Long id = 1L;
        Pet pet = new Pet();
        PetDto petDto = new PetDto();
        when(petRepository.findById(id)).thenReturn(Optional.of(pet));
        when(petMapper.modelToDto(pet)).thenReturn(petDto);

        PetDto result = petServiceImpl.getPet(id);

        assertNotNull(result);
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    void getPet_NotFound() {
        Long id = 1L;
        when(petRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PetServiceException.class, () -> petServiceImpl.getPet(id));
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    void updatePet() {
        PetDto petDto = new PetDto();
        petDto.setId(1L);
        Pet pet = new Pet();
        when(petRepository.findById(petDto.getId())).thenReturn(Optional.of(pet));
        when(petMapper.dtoToModel(petDto)).thenReturn(pet);
        when(petRepository.save(pet)).thenReturn(pet);
        when(petMapper.modelToDto(pet)).thenReturn(petDto);

        PetDto result = petServiceImpl.updatePet(petDto);

        assertNotNull(result);
        verify(petRepository, times(1)).findById(petDto.getId());
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void updatePet_NotFound() {
        PetDto petDto = new PetDto();
        petDto.setId(1L);
        when(petRepository.findById(petDto.getId())).thenReturn(Optional.empty());

        assertThrows(PetServiceException.class, () -> petServiceImpl.updatePet(petDto));
        verify(petRepository, times(1)).findById(petDto.getId());
    }

    @Test
    void deletePet() {
        Long id = 1L;

        petServiceImpl.deletePet(id);

        verify(petRepository, times(1)).deleteById(id);
    }
}