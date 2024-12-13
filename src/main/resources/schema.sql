CREATE TABLE BookCategory (
    book_category_id INT PRIMARY KEY,
    category_name VARCHAR(255)
);

CREATE TABLE Book (
    book_id INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    availability BOOLEAN,
    book_category_id INT,
    FOREIGN KEY (book_category_id) REFERENCES BookCategory(book_category_id)
);
