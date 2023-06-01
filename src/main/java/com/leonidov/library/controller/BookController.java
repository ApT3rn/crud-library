package com.leonidov.library.controller;

import com.leonidov.library.dao.BookRepo;
import com.leonidov.library.model.Book;
import com.leonidov.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showAllBooks(Model model) {
        model.addAttribute("allBooks", bookService.findAll());
        return "all-books";
    }

    @GetMapping("/add")
    public String addNewBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book-info";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveOrUpdate(book);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam(name = "bookId") int id,
                             Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty())
            return "redirect:/";
        model.addAttribute("book", book.get());
        return "book-info";
    }

    @RequestMapping("/delete")
    public String deleteBook(@RequestParam(name = "bookId") int id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
