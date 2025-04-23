package com.example.lab1.web;

import com.example.lab1.model.domain.BooksByAuthor;
import com.example.lab1.service.BooksByAuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksByAuthorController {

    private final BooksByAuthorService service;

    public BooksByAuthorController(BooksByAuthorService service) {
        this.service = service;
    }

    @GetMapping("/by-author")
    public List<BooksByAuthor> getBooksByAuthor() {
        return service.getBooksByAuthor();
    }
}
