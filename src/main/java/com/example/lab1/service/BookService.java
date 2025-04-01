package com.example.lab1.service;


import com.example.lab1.model.domain.Book;
import com.example.lab1.model.dto.BookDto;
import com.example.lab1.model.dto.CreateBookDto;
import com.example.lab1.model.dto.UpdateBookDto;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book save(CreateBookDto bookDto);

    Book update(Long id, UpdateBookDto bookDto);

    void deleteBook(Long id);

    void markBookAsTaken(Long id);

    List<Book> getAllBooksByPage(Pageable pageable);

    List<Book> getBooksByAvailableCopies(int minCopies);

    List<Book> getBooksSortedByAuthor();

}
