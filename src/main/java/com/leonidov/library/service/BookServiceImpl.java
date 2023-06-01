package com.leonidov.library.service;

import com.leonidov.library.dao.BookRepo;
import com.leonidov.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public void saveOrUpdate(Book book) {
        if (book.getId() != 0)
            this.bookRepo.update(book);
        else
            this.bookRepo.save(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return this.bookRepo.findById(id);
    }

    @Override
    public void deleteById(long id) {
        this.bookRepo.deleteById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepo.findByName(name);
    }
}
