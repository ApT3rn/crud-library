package com.leonidov.library.dao;

import com.leonidov.library.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepoImpl implements BookRepo {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private static final String SELECT_ALL_SQL = "SELECT * FROM books";
    private static final String UPDATE_BOOK_SQL =
            "UPDATE books SET name = ?, author = ?, " +
                    "publisher = ?, years = ?, genre = ? WHERE id = %s";
    private static final String INSERT_BOOK_SQL =
            "INSERT INTO books (name, author, publisher, years, genre) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BOOK_BY_ID_SQL =
            "SELECT * FROM books WHERE id = %d";
    private static final String SELECT_BOOK_BY_NAME_SQL =
            "SELECT * FROM books WHERE name = '%s'";
    private static final String DELETE_BOOK_BY_ID_SQL =
            "DELETE FROM books WHERE id = %d";

    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement(SELECT_ALL_SQL);
            result = statement.executeQuery();
            while (result.next()) {
                bookList.add(new Book(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        result.getString(6)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookList;
    }

    public boolean saveOrChange(Book book) {
        if (book.getName().isBlank())
            return false;

        try {
            connection = DriverManager.getConnection(url, username, password);
            if (book.getId() != 0)
                statement = connection.prepareStatement(
                        String.format(UPDATE_BOOK_SQL, book.getId()),
                        Statement.RETURN_GENERATED_KEYS);
            else
                statement = connection.prepareStatement(
                        INSERT_BOOK_SQL,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYears());
            statement.setString(5, book.getGenre());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    @Override
    public Optional<Book> getById(long id) {
        Book book = new Book();
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement(String.format(SELECT_BOOK_BY_ID_SQL, id));
            result = statement.executeQuery();
            while (result.next()) {
                book.setId(result.getInt(1));
                book.setName(result.getString(2));
                book.setAuthor(result.getString(3));
                book.setPublisher(result.getString(4));
                book.setYears(result.getInt(5));
                book.setGenre(result.getString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                result.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.of(book);
    }

    @Override
    public Optional<Book> getByName(String name) {
        Book book = new Book();
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement(String.format(SELECT_BOOK_BY_NAME_SQL, name));
            result = statement.executeQuery();
            while (result.next()) {
                book.setId(result.getInt(1));
                book.setName(result.getString(2));
                book.setAuthor(result.getString(3));
                book.setPublisher(result.getString(4));
                book.setYears(result.getInt(5));
                book.setGenre(result.getString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                result.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.of(book);
    }

    @Override
    public boolean deleteById(long id) {
        if (getById(id).isEmpty())
            return false;

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.prepareStatement(String.format(DELETE_BOOK_BY_ID_SQL, id));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
