package com.playtech.bookstore.service;

import com.playtech.bookstore.data.BookData;
import com.playtech.bookstore.jpa.BookEntity;
import com.playtech.bookstore.repository.BooksEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BooksServiceTest {
    @Mock
    BooksEntityRepository repository;

    @InjectMocks
    BooksService service;


    @Test
    void addBook() {
    }

    @Test
    void getBook() {
    }

    @Test
    void getBooks() {
    }
    @Test
    void changePrice() {
        //given
        BookEntity book = new BookEntity();
        book.setTitle("Test");
        book.setAuthor("Ken");
        book.setPages(1);
        book.setSummary("summary");
        book.setPublished(2023);
        book.setLanguage("EE");
        book.setPublisher("kirjastaja");
        book.setPrice(9.99);
        book.setImg_src("src");
        given(repository.findBookEntitiesByTitle("Test")).willReturn(book);
        //when
        Double newPrice = 19.99;
        service.changePrice(book.getTitle(), newPrice);
        //then
        then(repository).should().findBookEntitiesByTitle(book.getTitle());
        //then(repository).should().findBookEntitiesByPrice(newPrice);
    }

}