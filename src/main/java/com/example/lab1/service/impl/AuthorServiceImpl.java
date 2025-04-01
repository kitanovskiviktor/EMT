package com.example.lab1.service.impl;

import com.example.lab1.model.domain.Author;
import com.example.lab1.model.domain.Country;
import com.example.lab1.model.dto.AuthorDto;
import com.example.lab1.repository.AuthorRepository;
import com.example.lab1.repository.CountryRepository;
import com.example.lab1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author save(AuthorDto author) {
        Author a = new Author();
        return saveAuthor(author, a);
    }

    @Override
    public Author update(Long id, AuthorDto author) {
        Author a = authorRepository.findById(id).orElse(null);

        if (a == null) {
            return null;
        }

        return saveAuthor(author, a);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private Author saveAuthor(AuthorDto author, Author a) {
        Country c = countryRepository.findById(author.getCountryId()).orElse(null);

        if (c == null) {
            return null;
        }

        a.setName(author.getName());
        a.setSurname(author.getSurname());
        a.setCountry(c);

        return authorRepository.save(a);
    }
}
