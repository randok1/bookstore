package com.playtech.bookstore.controller;

import com.playtech.bookstore.data.BookData;
import com.playtech.bookstore.jpa.BookEntity;
import com.playtech.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @PostMapping("book")
    public void addBook(@RequestBody BookData book){
        booksService.addBook(book);
    }
    @GetMapping("book/{title}")
    public BookData getBook(@PathVariable("title") String title){
        return booksService.getBook(title);
    }

    @GetMapping("books")
    public List<BookData> getBooks(){
        return booksService.getBooks();
    }

}
