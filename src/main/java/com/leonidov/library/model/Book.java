package com.leonidov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String name;
    private String author;
    private String publisher;
    private int years;
    private String genre;

    public Book(String name, String author, String publisher, int years, String genre) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.years = years;
        this.genre = genre;
    }
}
