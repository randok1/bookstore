package com.playtech.bookstore.service;

import com.playtech.bookstore.data.BookData;
import com.playtech.bookstore.jpa.BookEntity;
import com.playtech.bookstore.repository.BooksEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksEntityRepository booksEntityRepository;

    public void addBook(BookData book){
        booksEntityRepository.save(new BookEntity(book.getTitle(), book.getAuthor(), book.getPages(), book.getSummary(), book.getPublished(), book.getLanguage(), book.getPublisher(), book.getPrice(), book.getImg_src()));
    }

    public BookData getBook (String title) {
        BookEntity bookEntity = booksEntityRepository.findBookEntitiesByTitle(title);
        BookData book = new BookData(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPages(), bookEntity.getSummary(), bookEntity.getPublished(), bookEntity.getLanguage(), bookEntity.getPublisher(), bookEntity.getPrice(), bookEntity.getImg_src());
        return book;
    }

    public List<BookData> getBooks(){
        List<BookEntity> bookEntities = booksEntityRepository.findAll();
        List<BookData> allBooks = new ArrayList<>();
        for (BookEntity bookEntity : bookEntities){
            allBooks.add(new BookData(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPages(), bookEntity.getSummary(), bookEntity.getPublished(), bookEntity.getLanguage(), bookEntity.getPublisher(), bookEntity.getPrice(), bookEntity.getImg_src()));
        }
        return allBooks;
    }
}
