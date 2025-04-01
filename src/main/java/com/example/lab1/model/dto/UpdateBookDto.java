package com.example.lab1.model.dto;

import com.example.lab1.model.domain.Category;
import lombok.Data;

@Data
public class UpdateBookDto {
    private String name;
    private Category category;
    private Integer availableCopies;
}
