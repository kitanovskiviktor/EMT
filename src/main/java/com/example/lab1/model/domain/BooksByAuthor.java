package com.example.lab1.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BooksByAuthor {

    @Id
    private Long authorId;
    private String name;
    private String surname;
    private Long booksCount;
}
