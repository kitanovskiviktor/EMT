package com.example.lab1.repository;

import com.example.lab1.model.domain.BooksByAuthor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BooksByAuthorRepository extends CrudRepository<BooksByAuthor, Long> {

    @Query("SELECT b FROM BooksByAuthor b")
    List<BooksByAuthor> findAllBooksByAuthor();
}
