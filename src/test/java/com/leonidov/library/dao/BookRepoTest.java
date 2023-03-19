package com.leonidov.library.dao;

import com.leonidov.library.model.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepoTest {

    @MockBean
    private BookRepo bookRepo;

    @Test
    void findAllEmpty() {
        assertIterableEquals(Collections.EMPTY_LIST, bookRepo.findAll());
    }

    @Test
    void findAllNotEmpty() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(0, "", "", "", 0, ""));
        bookList.add(new Book(1, "", "", "", 0, ""));
        Mockito.when(bookRepo.findAll()).thenReturn(bookList);

        assertIterableEquals(bookList, bookRepo.findAll());
        assertEquals(bookList.get(0).getId(), bookRepo.findAll().get(0).getId());

        List<Book> bookList1 = new ArrayList<>();
        bookList1.add(new Book(2, "", "", "", 0, ""));

        assertNotEquals(bookList1.get(0).getId(), bookRepo.findAll().get(0).getId());
    }

    @Test
    void saveOrChange() {
        Book book = new Book("Название", "", "", 0, "");

        if (!book.getName().isBlank())
            Mockito.when(bookRepo.saveOrChange(book)).thenReturn(true);

        assertFalse(bookRepo.saveOrChange(new Book()));
        assertTrue(bookRepo.saveOrChange(book));
    }

    @Test
    void getById() {
        Optional<Book> book = Optional.of(new Book(0, "", "", "", 0, ""));
        Mockito.when(bookRepo.getById(0)).thenReturn(book);

        assertEquals(book, bookRepo.getById(0));

        Optional<Book> book1 = Optional.of(new Book(1, "", "", "", 0, ""));

        assertNotEquals(book1, bookRepo.getById(0));
    }

    @Test
    void deleteById() {
        Mockito.when(bookRepo.deleteById(0)).thenReturn(true);

        assertTrue(bookRepo.deleteById(0));
        assertFalse(bookRepo.deleteById(1));
    }
}