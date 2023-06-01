package com.leonidov.library.service;

import com.leonidov.library.dao.BookRepo;
import com.leonidov.library.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    BookRepo bookRepo;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    void findAll() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        when(this.bookRepo.findAll()).thenReturn(List.of(book));
        List<Book> bookList = this.bookService.findAll();

        assertEquals(1, bookList.size());
        assertEquals(book.getName(), bookList.get(0).getName());
    }

    @Test
    void saveOrUpdate_ifBookNotExistsInDatabase() {
        Book book = new Book("Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookService.saveOrUpdate(book);
        verify(bookRepo, times(1)).save(book);
        verify(bookRepo, times(0)).update(book);
    }

    @Test
    void saveOrUpdate_ifBookExistsInDatabase() {
        Book book = new Book(1, "Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        this.bookService.saveOrUpdate(book);
        verify(bookRepo, times(0)).save(book);
        verify(bookRepo, times(1)).update(book);
    }

    @Test
    void findById() {
        Book book = new Book(1, "Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        when(this.bookRepo.findById(book.getId())).thenReturn(Optional.of(book));
        Optional<Book> bookFromDb = this.bookService.findById(book.getId());

        assertEquals(book, bookFromDb.get());
    }

    @Test
    void deleteById() {
        this.bookService.deleteById(1);

        verify(this.bookRepo, times(1)).deleteById(1);
    }

    @Test
    void findByName() {
        Book book = new Book(1, "Book1", "Author1",
                "Publisher1", 2023, "Genre1");

        when(this.bookRepo.findByName(book.getName())).thenReturn(Optional.of(book));
        Optional<Book> bookFromDb = this.bookService.findByName(book.getName());

        assertEquals(book, bookFromDb.get());
    }
}