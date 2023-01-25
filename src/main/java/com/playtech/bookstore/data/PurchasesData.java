package com.playtech.bookstore.data;

public class PurchasesData {
    private String books;
    private String client;
    private Double total;

    public PurchasesData(String books, String client, Double total) {
        this.books = books;
        this.client = client;
        this.total = total;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
