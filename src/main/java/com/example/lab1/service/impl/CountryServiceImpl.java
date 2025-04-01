package com.example.lab1.service.impl;

import com.example.lab1.model.domain.Country;
import com.example.lab1.model.dto.CountryDto;
import com.example.lab1.repository.CountryRepository;
import com.example.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long country) {
        return countryRepository.findById(country).orElse(null);
    }

    @Override
    public Country save(CountryDto country) {
        Country newCountry = new Country();

        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());

        return countryRepository.save(newCountry);
    }

    @Override
    public Country update(Long id, CountryDto country) {
        Country newCountry = countryRepository.findById(id).orElse(null);

        if (newCountry == null) {
            return null;
        }

        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());

        return countryRepository.save(newCountry);
    }


    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
