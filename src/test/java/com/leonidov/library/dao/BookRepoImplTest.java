package com.leonidov.library.dao;

import com.leonidov.library.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepoImplTest {

    @Autowired
    private BookRepo bookRepo;


    @Test
    void FindAll() {
        Book book1 = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");
        Book book2 = new Book("Book2", "Author2",
                "Publisher2", 2023, "Genre2");

        this.bookRepo.save(book1);
        this.bookRepo.save(book2);
        List<Book> bookList = this.bookRepo.findAll();

        assertEquals(2, bookList.size());
        assertEquals(book1.getName(), bookList.get(0).getName());
        this.bookRepo.clear();
    }

    @Test
    void save() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookRepo.save(book);
        List<Book> bookList = this.bookRepo.findAll();

        assertEquals(1, bookList.size());
        assertEquals(book.getName(), bookList.get(0).getName());
        this.bookRepo.clear();
    }

    @Test
    void update() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookRepo.save(book);
        List<Book> bookList = this.bookRepo.findAll();
        this.bookRepo.update(new Book(bookList.get(0).getId(), "fiea",
                "efoiw", "efwoi", 1, "fae"));
        List<Book> bookList2 = this.bookRepo.findAll();

        assertEquals(book.getName(), bookList.get(0).getName());
        assertEquals(1, bookList2.get(0).getYears());
        this.bookRepo.clear();
    }

    @Test
    void findById() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookRepo.save(book);
        Optional<Book> bookFromDb = this.bookRepo.findById(
                this.bookRepo.findByName(book.getName()).get().getId());

        assertEquals(book.getName(), bookFromDb.get().getName());
        this.bookRepo.clear();
    }

    @Test
    void findByName() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookRepo.save(book);
        Optional<Book> bookFromDb = this.bookRepo.findByName(book.getName());

        assertEquals(book.getName(), bookFromDb.get().getName());
        this.bookRepo.clear();
    }

    @Test
    void deleteById() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookRepo.save(book);
        List<Book> bookList1 = this.bookRepo.findAll();
        this.bookRepo.deleteById(
                this.bookRepo.findByName(book.getName()).get().getId());
        List<Book> bookList2 = this.bookRepo.findAll();

        assertEquals(1, bookList1.size());
        assertEquals(0, bookList2.size());
    }

    @Test
    void clear() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookRepo.save(book);
        List<Book> bookList1 = this.bookRepo.findAll();
        this.bookRepo.clear();
        List<Book> bookList2 = this.bookRepo.findAll();

        assertEquals(1, bookList1.size());
        assertEquals(0, bookList2.size());
    }
}