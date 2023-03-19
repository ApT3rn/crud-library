package com.leonidov.library.controller;

import com.leonidov.library.dao.BookRepo;
import com.leonidov.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookRepo bookRepo;

    @Autowired
    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/")
    public String showAllBooks(Model model) {
        model.addAttribute("allBooks", bookRepo.findAll());
        return "all-books";
    }

    @GetMapping("/add")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "book-info";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRepo.saveOrChange(book);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam(name = "bookId") int id,
                             Model model) {
        if (bookRepo.getById(id).isEmpty())
            return "redirect:/";
        model.addAttribute("book", bookRepo.getById(id).get());
        return "book-info";
    }

    @RequestMapping("/delete")
    public String deleteBook(@RequestParam(name = "bookId") int id) {
        bookRepo.deleteById(id);
        return "redirect:/";
    }
}
