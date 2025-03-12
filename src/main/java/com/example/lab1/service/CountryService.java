package com.example.lab1.service;

import com.example.lab1.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> save(Country country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);
}
