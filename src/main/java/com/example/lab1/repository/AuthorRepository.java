package com.example.lab1.repository;

import com.example.lab1.model.domain.Author;
import com.example.lab1.projection.AuthorNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<AuthorNameProjection> findAllBy();

    @Query(value = "SELECT country_id, country_name, authors_count FROM authors_by_country", nativeQuery = true)
    List<Object[]> fetchAuthorsByCountry();
}
