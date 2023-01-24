package com.playtech.bookstore.repository;

import com.playtech.bookstore.jpa.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BooksEntityRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findBookEntitiesByTitle(String title);
    BookEntity findBookEntitiesByAuthor(String author);
}
