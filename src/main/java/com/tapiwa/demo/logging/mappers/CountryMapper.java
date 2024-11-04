package com.tapiwa.demo.logging.mappers;

import com.tapiwa.demo.logging.dto.CountryDto;
import com.tapiwa.demo.logging.models.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryMapper {

    public Country dtoToModel(CountryDto countryDto) {
        Country country = new Country();
        country.setId(countryDto.getId());
        country.setCountryName(countryDto.getCountryName());
        country.setCountryCode(countryDto.getCountryCode());
        country.setDialingCode(countryDto.getDialingCode());
        country.setCurrencyCode(countryDto.getCurrencyCode());
        country.setInternetTLD(countryDto.getInternetTLD());
        return country;
    }

    public CountryDto modelToDto(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setCountryName(country.getCountryName());
        countryDto.setCountryCode(country.getCountryCode());
        countryDto.setDialingCode(country.getDialingCode());
        countryDto.setCurrencyCode(country.getCurrencyCode());
        countryDto.setInternetTLD(country.getInternetTLD());
        return countryDto;
    }
}
