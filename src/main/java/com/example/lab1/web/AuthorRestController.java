package com.example.lab1.web;


import com.example.lab1.model.domain.Author;
import com.example.lab1.model.dto.AuthorDto;
import com.example.lab1.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/api/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/api/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping("/api/authors/create")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorService.save(authorDto);
        if (author == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(author);
    }

    @PutMapping("/api/authors/{id}/update")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        Author author = authorService.update(id, authorDto);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/api/authors/{id}/delete")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
