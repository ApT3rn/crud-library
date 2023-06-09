package com.leonidov.library.dao;

import com.leonidov.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo {
    public List<Book> findAll();
    public void save(Book book);
    public void update(Book book);
    public Optional<Book> findById(long id);
    public void deleteById(long id);
    public Optional<Book> findByName(String name);
    public void clear();
}
