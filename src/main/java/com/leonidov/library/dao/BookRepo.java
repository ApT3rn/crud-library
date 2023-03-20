package com.leonidov.library.dao;

import com.leonidov.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo {
    public List<Book> findAll();
    public boolean saveOrChange(Book book);
    public Optional<Book> getById(long id);
    public boolean deleteById(long id);
    public Optional<Book> getByName(String name);
}
