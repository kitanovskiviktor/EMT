package com.example.lab1.service;

import com.example.lab1.model.domain.Author;
import com.example.lab1.model.domain.AuthorsByCountry;
import com.example.lab1.model.dto.AuthorDto;
import com.example.lab1.projection.AuthorNameProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author save(AuthorDto author);

    Author update(Long id, AuthorDto author);

    void deleteAuthor(Long id);

    List<AuthorsByCountry> getAuthorsByCountry();

    List<AuthorNameProjection> findAllBy();

}
