package com.playtech.bookstore.data;

public class BookData {
    private String title;
    private String author;
    private int pages;
    private String summary;
    private int published;
    private String language;
    private String publisher;
    private String img_src;

    public BookData(String title, String author, int pages, String summary, int published, String language, String publisher, Double price, String img_src) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.summary = summary;
        this.published = published;
        this.language = language;
        this.publisher = publisher;
        this.price = price;
        this.img_src = img_src;
    }

    public BookData() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private Double price;

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }
}
