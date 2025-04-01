package com.example.lab1.web;

import com.example.lab1.model.domain.Book;
import com.example.lab1.model.domain.Category;
import com.example.lab1.model.dto.BookDto;
import com.example.lab1.model.dto.CreateBookDto;
import com.example.lab1.model.dto.UpdateBookDto;
import com.example.lab1.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Get all book categories")
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get books by page")
    @GetMapping("/paged")
    public List<Book> getBooksByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookService.getAllBooksByPage((Pageable) PageRequest.of(page, size));
    }

    @Operation(summary = "Get a book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id) != null ?
                ResponseEntity.ok(bookService.getBookById(id)) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new book")
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto bookDto) {
        Book book = bookService.save(bookDto);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update an existing book")
    @PutMapping("/{id}/update")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookDto bookDto) {
        Book book = bookService.update(id, bookDto);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a book by ID")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Mark a book as taken")
    @PutMapping("/{id}/mark-taken")
    public ResponseEntity<Void> markBookAsTaken(@PathVariable Long id) {
        bookService.markBookAsTaken(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Get books with more than X available copies")
    @GetMapping("/available-copies")
    public List<Book> getBooksByAvailableCopies(@RequestParam int copies) {
        return bookService.getBooksByAvailableCopies(copies);
    }

    @Operation(summary = "Get books sorted by author ID in descending order")
    @GetMapping("/sorted-by-author")
    public List<Book> getBooksSortedByAuthor() {
        return bookService.getBooksSortedByAuthor();
    }

}
