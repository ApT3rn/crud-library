package com.leonidov.library.dao;

import com.leonidov.library.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BookRepoImplTest {

    @Autowired
    private BookRepo bookRepo;

    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        book1 = new Book("Book1", "Author1", "Publisher1", 2022, "Genre1");
        book2 = new Book("Book2", "Author2", "Publisher2", 2021, "Genre2");
        book3 = new Book("NewBook", "NewAuthor", "NewPublisher", 2023, "NewGenre");

        bookRepo.saveOrChange(book1);
        bookRepo.saveOrChange(book2);
    }

    @AfterEach
    public void tearDown() {
        bookRepo.deleteById(bookRepo.getByName(book1.getName()).get().getId());
        bookRepo.deleteById(bookRepo.getByName(book2.getName()).get().getId());
        bookRepo.deleteById(bookRepo.getByName(book3.getName()).get().getId());
    }

    @Test
    void testFindAll() {
        List<Book> bookList = bookRepo.findAll();

        assertEquals(2, bookList.size());
        assertEquals(bookList.get(0).getName(), book1.getName());
        assertEquals(bookList.get(1).getName(), book2.getName());
    }

    @Test
    void testSaveOrChange() {
        assertTrue(bookRepo.saveOrChange(book3));

        Optional<Book> optionalBook = bookRepo.getByName(book3.getName());

        assertTrue(optionalBook.isPresent());
        assertEquals(book3.getName(), optionalBook.get().getName());
    }

    @Test
    void testGetById() {
        Optional<Book> optionalBook = bookRepo.getById(bookRepo.getByName(book1.getName()).get().getId());

        assertTrue(optionalBook.isPresent());
        assertEquals(book1.getName(), optionalBook.get().getName());
    }

    @Test
    void testGetByName() {
        Optional<Book> optionalBook = bookRepo.getByName(book1.getName());

        assertTrue(optionalBook.isPresent());
        assertEquals(book1.getName(), optionalBook.get().getName());
    }

    @Test
    void testDeleteById() {
        Optional<Book> optionalBook = bookRepo.getByName(book1.getName());

        assertTrue(bookRepo.deleteById(optionalBook.get().getId()));

        Optional<Book> optionalBook2 = bookRepo.getByName(book1.getName());

        assertEquals(0, optionalBook2.get().getId());
    }
}