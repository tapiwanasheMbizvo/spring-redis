package com.tapiwa.demo.logging.services.interfaces;

import com.tapiwa.demo.logging.dto.CountryDto;

import java.util.List;

public interface CountryService {
    CountryDto saveCountry(CountryDto countryDto);
    CountryDto getCountry(Long id);
    CountryDto updateCountry(CountryDto countryDto);
    void deleteCountry(Long id);
    List<CountryDto> getAllCountries();

    CountryDto getCountryByCode(String code);

    CountryDto getByCountryId(Long id);
}
