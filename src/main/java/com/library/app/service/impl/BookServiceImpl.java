package com.library.app.service.impl;

import com.library.app.entity.Book;
import com.library.app.exception.NotFoundException;
import com.library.app.repository.BookRepository;
import com.library.app.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getId());
    }

    @Override
    public Page<Book> findPaginated(Pageable pageable) {
        long startTime = System.currentTimeMillis();

        List<Book> allBooks = findAllBooks();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;

        if (allBooks.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allBooks.size());
            list = allBooks.subList(startItem, toIndex);
        }

        var bookPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allBooks.size());

        long endTime = System.currentTimeMillis();

        return bookPage;
    }

}
