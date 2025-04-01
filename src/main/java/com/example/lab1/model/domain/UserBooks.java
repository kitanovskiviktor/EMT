package com.example.lab1.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    private String username;

    public UserBooks() {
    }

    public UserBooks(Long bookId, String username) {
        this.bookId = bookId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}