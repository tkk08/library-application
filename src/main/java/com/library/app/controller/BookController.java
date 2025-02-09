package com.library.app.controller;

import com.library.app.entity.Author;
import com.library.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping({ "/books", "/" })
    public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);

        var bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("books", bookPage);

        var totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list-books";
    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {

        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("book", bookService.findBookById(id));
        return "list-book";
    }

}
