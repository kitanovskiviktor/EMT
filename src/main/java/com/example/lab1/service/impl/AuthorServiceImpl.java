package com.example.lab1.service.impl;

import com.example.lab1.model.Author;
import com.example.lab1.model.dto.AuthorDto;
import com.example.lab1.repository.AuthorRepository;
import com.example.lab1.service.AuthorService;
import com.example.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(AuthorDto author) {
        if (author.getCountryId() != null &&
                countryService.findById(author.getCountryId()).isPresent()) {
            return Optional.of(
                    authorRepository.save(new Author(author.getName(), author.getSurname(),
                            countryService.findById(author.getCountryId()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    if (author.getName() != null) {
                        existingAuthor.setName(author.getName());
                    }
                    if (author.getSurname() != null) {
                        existingAuthor.setSurname(author.getSurname());
                    }
                    if (author.getCountryId() != null && countryService.findById(author.getCountryId()).isPresent()) {
                        existingAuthor.setCountry(countryService.findById(author.getCountryId()).get());
                    }
                    return authorRepository.save(existingAuthor);
                });
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
