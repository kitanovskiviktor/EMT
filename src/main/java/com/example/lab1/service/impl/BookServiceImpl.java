package com.example.lab1.service.impl;

import com.example.lab1.model.domain.Author;
import com.example.lab1.model.domain.Book;
import com.example.lab1.model.domain.Category;
import com.example.lab1.model.dto.BookDto;
import com.example.lab1.model.dto.CreateBookDto;
import com.example.lab1.model.dto.UpdateBookDto;
import com.example.lab1.repository.BookRepository;
import com.example.lab1.service.AuthorService;
import com.example.lab1.service.BookService;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(CreateBookDto bookDto) {
        Author author = authorService.getAuthorById(bookDto.getAuthor());
        if (author == null) return null;

        Book book = new Book();
        book.setName(bookDto.getName().isEmpty() ? "Unnamed Book" : bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(Math.max(bookDto.getAvailableCopies(), 0));

        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, UpdateBookDto bookDto) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) return null;

        if (bookDto.getName() != null && !bookDto.getName().isEmpty()) {
            book.setName(bookDto.getName());
        }
        if (bookDto.getCategory() != null) {
            book.setCategory(bookDto.getCategory());
        }
        if (bookDto.getAvailableCopies() != null) {
            book.setAvailableCopies(Math.max(bookDto.getAvailableCopies(), 0));
        }

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null && book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        }
    }

    @Override
    public List<Book> getAllBooksByPage(Pageable withPage) {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAvailableCopies(int minCopies) {
        return bookRepository.findByAvailableCopiesGreaterThan(minCopies);
    }

    @Override
    public List<Book> getBooksSortedByAuthor() {
        return bookRepository.findAllByOrderByAuthorIdDesc();
    }
}
