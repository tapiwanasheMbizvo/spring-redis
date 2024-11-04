package com.tapiwa.demo.logging.controllers;

import com.tapiwa.demo.logging.controllers.response.HttpResponse;
import com.tapiwa.demo.logging.dto.CountryDto;
import com.tapiwa.demo.logging.services.interfaces.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private  final CountryService countryService;


    @GetMapping
    public ResponseEntity<HttpResponse> getAllCountries() {
        log.info("Fetching all countries");
        List<CountryDto> countries = countryService.getAllCountries();
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("All countries fetched successfully")
                .localDateTime(LocalDateTime.now())
                .data(Map.of("countries", countries))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getCountryByCode(@PathVariable Long id) {
        log.info("Fetching country with code: {}", id);
        CountryDto country = countryService.getByCountryId(id);
        return ResponseEntity.ok(HttpResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Country fetched successfully")
                .localDateTime(LocalDateTime.now())
                .data(Map.of("country", country))
                .build());
    }
}
