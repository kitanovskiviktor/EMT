CREATE TABLE IF NOT EXISTS country (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
    continent VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS author (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    country_id BIGINT,
    FOREIGN KEY (country_id) REFERENCES country(id)
    );

CREATE TABLE IF NOT EXISTS category (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS book (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(200) NOT NULL,
    category_id BIGINT,
    author_id BIGINT,
    available_copies INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
    );

CREATE TABLE IF NOT EXISTS user_books (
                                          id SERIAL PRIMARY KEY,
                                          book_id BIGINT,
                                          username VARCHAR(100) NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id)
    );

CREATE TABLE IF NOT EXISTS wishlist (
                                        id SERIAL PRIMARY KEY,
                                        book_id BIGINT,
                                        rented BOOLEAN NOT NULL DEFAULT FALSE,
                                        FOREIGN KEY (book_id) REFERENCES book(id)
    );
