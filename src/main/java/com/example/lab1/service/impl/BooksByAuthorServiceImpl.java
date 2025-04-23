package com.example.lab1.service.impl;

import com.example.lab1.model.domain.BooksByAuthor;
import com.example.lab1.repository.BooksByAuthorRepository;
import com.example.lab1.service.BooksByAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksByAuthorServiceImpl implements BooksByAuthorService {

    private final BooksByAuthorRepository repository;

    public BooksByAuthorServiceImpl(BooksByAuthorRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<BooksByAuthor> getBooksByAuthor() {
        return repository.findAllBooksByAuthor();
    }
}
