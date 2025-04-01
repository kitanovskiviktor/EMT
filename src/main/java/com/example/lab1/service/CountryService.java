package com.example.lab1.service;

import com.example.lab1.model.domain.Country;
import com.example.lab1.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(Long country);

    Country save(CountryDto country);

    Country update(Long id, CountryDto country);

    void deleteCountry(Long id);
}
