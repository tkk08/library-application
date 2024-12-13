package com.library.app.controller;

import com.library.app.entity.Book;
import com.library.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @PostMapping("/reserve/{bookId}")
    public String reserveBook(@PathVariable int bookId) {
        return bookService.reserveBook(bookId);
    }
}
