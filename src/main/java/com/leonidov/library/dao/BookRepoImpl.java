package com.leonidov.library.dao;

import com.leonidov.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepoImpl implements BookRepo, RowMapper<Book> {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public BookRepoImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT_ALL_SQL = "SELECT * FROM books";
    private static final String UPDATE_BOOK_SQL =
            "UPDATE books SET name = ?, author = ?, " +
                    "publisher = ?, years = ?, genre = ? WHERE id = ?";
    private static final String INSERT_BOOK_SQL =
            "INSERT INTO books (name, author, publisher, years, genre) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BOOK_BY_ID_SQL =
            "SELECT * FROM books WHERE id = ?";
    private static final String SELECT_BOOK_BY_NAME_SQL =
            "SELECT * FROM books WHERE name = ?";
    private static final String DELETE_BOOK_BY_ID_SQL =
            "DELETE FROM books WHERE id = ?";
    private static final String DELETE_ALL_IN_TABLE_SQL =
            "TRUNCATE TABLE books";

    @Override
    public List<Book> findAll() {
        return this.jdbcOperations.query(SELECT_ALL_SQL, this);
    }

    @Override
    public void save(Book book) {
        this.jdbcOperations.update(
                INSERT_BOOK_SQL,
                book.getName(), book.getAuthor(),
                book.getPublisher(), book.getYears(), book.getGenre());
    }

    @Override
    public void update(Book book) {
        this.jdbcOperations.update(UPDATE_BOOK_SQL,
                book.getName(), book.getAuthor(), book.getPublisher(),
                book.getYears(), book.getGenre(), book.getId());
    }

    @Override
    public Optional<Book> findById(long id) {
        return this.jdbcOperations.query(
                SELECT_BOOK_BY_ID_SQL, new Object[]{id},
                this).stream().findFirst();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.jdbcOperations.query(
                SELECT_BOOK_BY_NAME_SQL, new Object[]{name},
                this).stream().findFirst();
    }

    @Override
    public void deleteById(long id) {
        this.jdbcOperations.update(DELETE_BOOK_BY_ID_SQL, id);
    }

    @Override
    public void clear() {
        this.jdbcOperations.update(DELETE_ALL_IN_TABLE_SQL);
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getInt("id"),
                rs.getString("name"), rs.getString("author"),
                rs.getString("publisher"), rs.getInt("years"),
                rs.getString("genre"));
    }
}
