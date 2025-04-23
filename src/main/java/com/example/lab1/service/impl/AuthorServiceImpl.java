package com.example.lab1.service.impl;

import com.example.lab1.events.AuthorChangedEvent;
import com.example.lab1.model.domain.Author;
import com.example.lab1.model.domain.AuthorsByCountry;
import com.example.lab1.model.domain.Country;
import com.example.lab1.model.dto.AuthorDto;
import com.example.lab1.projection.AuthorNameProjection;
import com.example.lab1.repository.AuthorRepository;
import com.example.lab1.repository.CountryRepository;
import com.example.lab1.service.AuthorService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    private final ApplicationEventPublisher eventPublisher;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository, ApplicationEventPublisher eventPublisher) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.eventPublisher = eventPublisher;
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
        eventPublisher.publishEvent(new AuthorChangedEvent(this));
        return saveAuthor(author, a);
    }

    @Override
    public Author update(Long id, AuthorDto author) {
        Author a = authorRepository.findById(id).orElse(null);

        if (a == null) {
            return null;
        }

        eventPublisher.publishEvent(new AuthorChangedEvent(this));

        return saveAuthor(author, a);
    }

    @Override
    public void deleteAuthor(Long id) {
        eventPublisher.publishEvent(new AuthorChangedEvent(this));
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

        eventPublisher.publishEvent(new AuthorChangedEvent(this));

        return authorRepository.save(a);
    }

    public List<AuthorsByCountry> getAuthorsByCountry() {
        return authorRepository.fetchAuthorsByCountry().stream().map(row ->
                new AuthorsByCountry(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        ((Number) row[2]).longValue()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<AuthorNameProjection> findAllBy() {
        return this.authorRepository.findAllBy();
    }
}
