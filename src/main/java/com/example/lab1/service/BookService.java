package com.example.lab1.service;


import com.example.lab1.model.Book;
import com.example.lab1.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> save(BookDto book);

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, BookDto book);

    void deleteById(Long id);

    Optional<Book> rentBook(Long id);

}
