package com.tapiwa.demo.logging.controllers;

import com.tapiwa.demo.logging.dto.PetDto;
import com.tapiwa.demo.logging.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
class PetControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PetController(petService)).build();
    }

    @Test
    void getAllPets() throws Exception {
        when(petService.getAllPets()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("All pets fetched successfully"))
                .andExpect(jsonPath("$.data.pets").isEmpty());
    }

    @Test
    void getSinglePet() throws Exception {
        PetDto petDto = new PetDto();
        petDto.setId(1L);
        when(petService.getPet(1L)).thenReturn(petDto);

        mockMvc.perform(get("/api/v1/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pet fetched successfully"))
                .andExpect(jsonPath("$.data.pet.id").value(1L));
    }

    @Test
    void savePet() throws Exception {
        PetDto petDto = new PetDto();
        petDto.setId(1L);
        when(petService.savePet(petDto)).thenReturn(petDto);

        mockMvc.perform(post("/api/v1/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pet saved successfully"))
                .andExpect(jsonPath("$.data.pet.id").value(1L));
    }

    @Test
    void deletePet() throws Exception {
        mockMvc.perform(delete("/api/v1/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pet deleted successfully"));
    }

    @Test
    void updatePet() throws Exception {
        PetDto petDto = new PetDto();
        petDto.setId(1L);
        when(petService.updatePet(petDto)).thenReturn(petDto);

        mockMvc.perform(put("/api/v1/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Pet updated successfully"))
                .andExpect(jsonPath("$.data.pet.id").value(1L));
    }
}