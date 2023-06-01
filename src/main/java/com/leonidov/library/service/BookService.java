package com.leonidov.library.service;

import com.leonidov.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();
    public void saveOrUpdate(Book book);
    public Optional<Book> findById(long id);
    public void deleteById(long id);
    public Optional<Book> findByName(String name);
}
