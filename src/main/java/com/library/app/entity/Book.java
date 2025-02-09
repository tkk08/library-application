package com.library.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = { @JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<Category>();
    public Book(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addAuthors(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthors(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addCategories(Category category) {
        this.categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategories(Category category) {
        this.categories.remove(category);
        category.getBooks().remove(this);
    }

}

