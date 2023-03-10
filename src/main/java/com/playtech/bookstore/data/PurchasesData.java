package com.playtech.bookstore.data;

public class PurchasesData {
    private String books;
    private Integer client;
    private Double total;

    public PurchasesData(String books, Integer client, Double total) {
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

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
