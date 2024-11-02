package com.tapiwa.demo.logging.controllers;

import com.tapiwa.demo.logging.controllers.response.HttpResponse;
import com.tapiwa.demo.logging.dto.PetDto;
import com.tapiwa.demo.logging.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }


    @GetMapping
    public ResponseEntity<HttpResponse> getAllPets() {

        log.info("Fetching all pets");
        List<PetDto> pets = petService.getAllPets();
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("All pets fetched successfully")
                .localDateTime(LocalDateTime.now())
                .data(Map.of("pets", pets))
                .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getSinglePet(@PathVariable Long id) {
        log.info("Fetching pet with id: {}", id);
        PetDto pet = petService.getPet(id);
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Pet fetched successfully")
                .localDateTime(LocalDateTime.now())
                .data(Map.of("pet", pet))
                .build());
    }

    @PostMapping
    public ResponseEntity<HttpResponse> savePet(@RequestBody PetDto petDto) {
        log.info("Saving pet: {}", petDto);
        PetDto pet = petService.savePet(petDto);
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Pet saved successfully")
                .localDateTime(LocalDateTime.now())
                .data(Map.of("pet", pet))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deletePet(@PathVariable Long id) {
        log.info("Deleting pet with id: {}", id);
        petService.deletePet(id);
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Pet deleted successfully")
                .localDateTime(LocalDateTime.now())
                .build());
    }

    @PutMapping
    public ResponseEntity<HttpResponse> updatePet(@RequestBody PetDto petDto) {
        log.info("Updating pet: {}", petDto);
        PetDto pet = petService.updatePet(petDto);
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Pet updated successfully")
                .localDateTime(LocalDateTime.now())
                .data(Map.of("pet", pet))
                .build());
    }

}
