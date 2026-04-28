package com.example.lekcja17_formularzeprzygotowaniedokartkowki;

public class Book {
    private String title;
    private String author;
    private String genre;
    private boolean isNew;
    private double price;
    private int promo;
    private String available;
    private String ageCategory;

    public Book(String title, String author, String genre, boolean isNew,
                double price, int promo, String available, String ageCategory) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isNew = isNew;
        this.price = price;
        this.promo = promo;
        this.available = available;
        this.ageCategory = ageCategory;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isNew() { return isNew; }
    public double getPrice() { return price; }
    public int getPromo() { return promo; }
    public String getAvailable() { return available; }
    public String getAgeCategory() { return ageCategory; }
}
