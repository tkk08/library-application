package com.library.app.service;
import com.library.app.entity.Book;
import com.library.app.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public String reserveBook(int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (!book.isAvailability()) {
                return "Book is already reserved.";
            }
            book.setAvailability(false); // Set availability to false
            bookRepository.save(book);   // Save updated book
            return "Book reserved successfully.";
        } else {
            return "Book not found.";
        }
    }


}

