package com.example.lab1.service;

import com.example.lab1.model.Author;
import com.example.lab1.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> save(AuthorDto author);

    Optional<Author> findById(Long id);

    Optional<Author> update(Long id, AuthorDto author);

    void deleteById(Long id);

}
