package com.example.lab1.model.domain;

public class AuthorsByCountry {
    public Long countryId;
    public String countryName;
    public Long authorsCount;

    public AuthorsByCountry(Long countryId, String countryName, Long authorsCount) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.authorsCount = authorsCount;
    }
}
