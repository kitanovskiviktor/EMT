package com.example.lab1.service.impl;

import com.example.lab1.model.Book;
import com.example.lab1.model.Category;
import com.example.lab1.model.dto.BookDto;
import com.example.lab1.repository.BookRepository;
import com.example.lab1.service.AuthorService;
import com.example.lab1.service.BookService;
import org.springframework.stereotype.Service;

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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Override
    public Optional<Book> save(BookDto bookDto) {
        if (bookDto.getAuthor() != null &&
                authorService.findById(bookDto.getAuthor()).isPresent()) {
            return Optional.of(
                        bookRepository.save(new Book(
                                bookDto.getName(),
                                Category.valueOf(bookDto.getCategory().toUpperCase()), // Конверзија од String во Enum
                                authorService.findById(bookDto.getAuthor()).get(),
                                bookDto.getAvailableCopies())
                        )
            );
        }
        return Optional.empty();
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, BookDto book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if(book.getName() != null) {
                        existingBook.setName(book.getName());
                    }
                    if (book.getCategory() != null) {
                        existingBook.setCategory(Category.valueOf(book.getCategory().toUpperCase()));
                    }
                    if (book.getAvailableCopies() != null) {
                        existingBook.setAvailableCopies(book.getAvailableCopies());
                    }
                    if (book.getAuthor() != null && authorService.findById(book.getAuthor()).isPresent()) {
                        existingBook.setAuthor(authorService.findById(book.getAuthor()).get());
                    }
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rentBook(Long id) {
        Optional<Book> b = bookRepository.findById(id);
        if(!b.isPresent()){
            throw new RuntimeException("book not found");
        }
        Book book = b.get();
        if(book.getAvailableCopies()<=0){
            throw new RuntimeException("not enough books");
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
