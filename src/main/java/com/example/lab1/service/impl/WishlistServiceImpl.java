package com.example.lab1.service.impl;

import com.example.lab1.model.domain.Book;
import com.example.lab1.model.domain.Wishlist;
import com.example.lab1.repository.BookRepository;
import com.example.lab1.repository.WishlistRepository;
import com.example.lab1.service.WishlistService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, BookRepository bookRepository) {
        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBookToWishlist(Long bookId) {
        Optional<Book> book = bookRepository.findAvailableBookById(bookId);

        if (book.isPresent()) {
            Wishlist wishlist = new Wishlist(book.get());
            wishlistRepository.save(wishlist);
        } else {
            throw new IllegalStateException("No available copies of this book.");
        }
    }

    @Override
    @Transactional
    public void rentAllBooks() {
        var wishlistItems = wishlistRepository.findAll();

        for (Wishlist item : wishlistItems) {
            Optional<Book> book = bookRepository.findAvailableBookById(item.getBook().getId());
            if (book.isPresent()) {
                bookRepository.decreaseAvailableCopies(book.get().getId());
                item.setRented(true);
                wishlistRepository.save(item);
            } else {
                throw new IllegalStateException("One or more books have no available copies.");
            }
        }
    }
}
