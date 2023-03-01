package com.playtech.bookstore.service;

import com.playtech.bookstore.data.BookData;
import com.playtech.bookstore.data.ClientData;
import com.playtech.bookstore.jpa.BookEntity;
import com.playtech.bookstore.jpa.ClientEntity;
import com.playtech.bookstore.jpa.PurchasesEntity;
import com.playtech.bookstore.repository.BooksEntityRepository;
import com.playtech.bookstore.repository.PurchasesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksEntityRepository booksEntityRepository;
    @Autowired
    private PurchasesEntityRepository purchasesEntityRepository;

    public void addBook(BookData book){
        booksEntityRepository.save(new BookEntity(book.getTitle(), book.getAuthor(), book.getPages(), book.getSummary(), book.getPublished(), book.getLanguage(), book.getPublisher(), book.getPrice(), book.getImg_src()));
    }
    public BookData getBook (String title) {
        BookEntity bookEntity = booksEntityRepository.findBookEntitiesByTitle(title);
        BookData book = new BookData(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPages(), bookEntity.getSummary(), bookEntity.getPublished(), bookEntity.getLanguage(), bookEntity.getPublisher(), bookEntity.getPrice(), bookEntity.getImg_src());
        return book;
    }
    public BookData getBookById (Integer id) {
        BookEntity bookEntity = booksEntityRepository.findBookEntityById(id);
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
    ClientService clientService;
    public List<BookData> getClientBooks(String clientName){
        List<PurchasesEntity> purchasesEntities = purchasesEntityRepository.findAllByClient(clientService.getClientId(clientName));
        List<BookData> books = new ArrayList<>();
        for (PurchasesEntity purchase:purchasesEntities) {
            books.add(getBook(purchase.getBooks()));
        }
        return books;
    }

    public void changePrice(String title, Double price) {
        BookEntity bookEntity = booksEntityRepository.findBookEntitiesByTitle(title);
        bookEntity.setPrice(price);
        booksEntityRepository.save(bookEntity);
    }
}
