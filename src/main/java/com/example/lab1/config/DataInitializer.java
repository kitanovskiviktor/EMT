package com.example.lab1.config;

import com.example.lab1.model.Author;
import com.example.lab1.model.Book;
import com.example.lab1.model.Category;
import com.example.lab1.model.Country;
import com.example.lab1.repository.AuthorRepository;
import com.example.lab1.repository.BookRepository;
import com.example.lab1.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        Country macedonia = countryRepository.save(new Country("Macedonia", "Europe"));
        Country usa = countryRepository.save(new Country("USA", "North America"));
        Country france = countryRepository.save(new Country("France", "Europe"));
        Country japan = countryRepository.save(new Country("Japan", "Asia"));
        Country brazil = countryRepository.save(new Country("Brazil", "South America"));

        Author petre = authorRepository.save(new Author("Petre", "Andreevski", macedonia));
        Author mark = authorRepository.save(new Author("Mark", "Twain", usa));
        Author victor = authorRepository.save(new Author("Victor", "Hugo", france));
        Author haruki = authorRepository.save(new Author("Haruki", "Murakami", japan));
        Author paulo = authorRepository.save(new Author("Paulo", "Coelho", brazil));

        bookRepository.save(new Book("Pirej", Category.NOVEL, petre, 5));
        bookRepository.save(new Book("Tom Sawyer", Category.HISTORY, mark, 3));
        bookRepository.save(new Book("Les Misérables", Category.DRAMA, victor, 2));
        bookRepository.save(new Book("Norwegian Wood", Category.FANTASY, haruki , 4));
        bookRepository.save(new Book("The Alchemist", Category.CLASSICS, paulo, 6));
    }
}
