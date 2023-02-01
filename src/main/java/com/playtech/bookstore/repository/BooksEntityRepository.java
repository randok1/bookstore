package com.playtech.bookstore.repository;

import com.playtech.bookstore.jpa.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksEntityRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findBookEntitiesByTitle(String title);
    BookEntity findBookEntitiesByAuthor(String author);
}
