package com.tapiwa.demo.logging.services.impl;

import com.tapiwa.demo.logging.dto.CountryDto;
import com.tapiwa.demo.logging.mappers.CountryMapper;
import com.tapiwa.demo.logging.repositories.CountryRepository;
import com.tapiwa.demo.logging.services.exceptions.CountryServiceException;
import com.tapiwa.demo.logging.services.interfaces.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private  final CountryMapper countryMapper;
    private  final CountryRepository countryRepository;



    @Override
    public CountryDto saveCountry(CountryDto countryDto) {

        return countryMapper.modelToDto(countryRepository.save(countryMapper.dtoToModel(countryDto)));
    }

    @Override
    public CountryDto getCountry(Long id) {

        if (countryRepository.findById(id).isEmpty()){
            throw  new CountryServiceException("Country not found with id: "+id);
        }
        return countryMapper.modelToDto(countryRepository.findById(id).get());
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto) {

        return countryMapper.modelToDto(countryRepository.save(countryMapper.dtoToModel(countryDto)));
    }

    @Override
    public void deleteCountry(Long id) {

        countryRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "countriesCache")
    public List<CountryDto> getAllCountries() {

        return  countryRepository.findAll().stream().map(countryMapper::modelToDto).toList();
    }
    @Override
    @Cacheable(value = "countryCache")
    public CountryDto getByCountryId(Long id) {
        if (countryRepository.findById(id).isEmpty()){
            throw  new CountryServiceException("Country not found with id: "+id);
        }
        return countryMapper.modelToDto(countryRepository.findById(id).get());
    }
    @Override

    public CountryDto getCountryByCode(String code) {
        if (countryRepository.findByCountryCode(code).isEmpty()){
            throw  new CountryServiceException("Country not found with code: "+code);
        }
        return countryMapper.modelToDto(countryRepository.findByCountryCode(code).get());
    }


}
