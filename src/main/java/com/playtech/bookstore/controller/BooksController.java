package com.playtech.bookstore.controller;

import com.playtech.bookstore.data.BookData;
import com.playtech.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @PostMapping("api/book")
    public void addBook(@RequestBody BookData book){
        booksService.addBook(book);
    }
    @GetMapping("api/book/{title}")
    public BookData getBook(@PathVariable("title") String title){
        return booksService.getBook(title);
    }


    @GetMapping("api/books/{client}")
    public List<BookData> getClientBooks(@PathVariable("client") String clientName){
        return booksService.getClientBooks(clientName);
    }

    @PutMapping("api/book/{title}/{price}")
    public String changePrice(@PathVariable("title") String title, @PathVariable("price") Double price) {
        booksService.changePrice(title, price);
        return title + " price was changed to " + price;
    }

}
