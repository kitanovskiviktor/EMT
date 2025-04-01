package com.example.lab1.web;

import com.example.lab1.service.WishlistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistRestController {

    private final WishlistService wishlistService;

    public WishlistRestController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public String addBookToWishlist(@RequestParam Long bookId) {
        try {
            wishlistService.addBookToWishlist(bookId);
            return "Book added to wishlist successfully!";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/rent")
    public String rentAllBooks() {
        try {
            wishlistService.rentAllBooks();
            return "Books rented successfully!";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}