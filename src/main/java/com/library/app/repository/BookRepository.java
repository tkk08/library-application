package com.library.app.repository;

import com.library.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%")
//    + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
    public List<Book> search(String keyword);
}

