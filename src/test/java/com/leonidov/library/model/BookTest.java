package com.leonidov.library.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    private Book book = new Book(1, "Имя", "Автор",
            "Издательство", 2023, "Фантастика");

    @Test
    void getId() {
        assertEquals(1, book.getId());
    }

    @Test
    void getName() {
        assertEquals("Имя", book.getName());
    }

    @Test
    void getAuthor() {
        assertEquals("Автор", book.getAuthor());
    }

    @Test
    void getPublisher() {
        assertEquals("Издательство", book.getPublisher());
    }

    @Test
    void getYears() {
        assertEquals(2023, book.getYears());
    }

    @Test
    void getGenre() {
        assertEquals("Фантастика", book.getGenre());
    }

    @Test
    void setId() {
        book.setId(2);
        assertEquals(2, book.getId());
    }

    @Test
    void setName() {
        book.setName("Новая книга");
        assertEquals("Новая книга", book.getName());
    }

    @Test
    void setAuthor() {
        book.setAuthor("Новый автор");
        assertEquals("Новый автор", book.getAuthor());
    }

    @Test
    void setPublisher() {
        book.setPublisher("Новое издательство");
        assertEquals("Новое издательство", book.getPublisher());
    }

    @Test
    void setYears() {
        book.setYears(2024);
        assertEquals(2024, book.getYears());
    }

    @Test
    void setGenre() {
        book.setGenre("История");
        assertEquals("История", book.getGenre());
    }
}