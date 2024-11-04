package com.tapiwa.demo.logging.repositories;

import com.tapiwa.demo.logging.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByCountryName(String countryName);
    Optional<Country> findByCountryCode(String countryCode);
    Optional<Country> findByDialingCode(String dialingCode);
    Optional<Country> findByCurrencyCode(String currencyCode);
    Optional<Country> findByInternetTLD(String internetTLD);
}
