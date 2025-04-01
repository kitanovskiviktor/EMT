package com.example.lab1.repository;

import com.example.lab1.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailableCopiesGreaterThan(int copies);
    List<Book> findAllByOrderByAuthorIdDesc();

    @Query("SELECT b FROM Book b WHERE b.availableCopies > :copies")
    List<Book> findBooksByAvailableCopiesGreaterThan(@Param("copies") int copies);

    @Query("SELECT b FROM Book b ORDER BY b.author.id DESC")
    List<Book> findAllBooksSortedByAuthorIdDesc();
    @Query("SELECT b FROM Book b WHERE b.id = :bookId AND b.availableCopies > 0")
    Optional<Book> findAvailableBookById(@Param("bookId") Long bookId);

    @Modifying
    @Query("UPDATE Book b SET b.availableCopies = b.availableCopies - 1 WHERE b.id = :bookId AND b.availableCopies > 0")
    void decreaseAvailableCopies(@Param("bookId") Long bookId);
}
