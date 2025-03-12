package com.example.lab1.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class BookDto {
    private String name;

    private String category;

    private Long author;

    private Integer availableCopies;

    public BookDto() {}

    public BookDto(String name, String category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Long getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
