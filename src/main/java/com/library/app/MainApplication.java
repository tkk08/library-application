package com.library.app;

import com.library.app.entity.*;
import com.library.app.repository.UserRepository;
import com.library.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MainApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {
			var user = new User("admin", "admin", "admin@gmail.com", passwordEncoder.encode("admin"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

			var book = new Book("Spring in Action ", "Book description");
			book.addAuthors(new Author("Matt", "dummy description"));
			book.addCategories(new Category("Dummy category"));
			bookService.createBook(book);

			var book1 = new Book("Spring Microservices", "Description1");
			book1.addAuthors(new Author("Maxwell", "Test description1"));
			book1.addCategories(new Category("New category"));
			bookService.createBook(book1);

			var book2 = new Book("Spring Boot", "description2");
			book2.addAuthors(new Author("Josh Lang", "Test description2"));
			book2.addCategories(new Category("Spring category"));
			bookService.createBook(book2);
		};
	}


}
