INSERT INTO BookCategory (book_category_id, category_name) VALUES
(1, 'Fiction'),
(2, 'Non-Fiction'),
(3, 'Science Fiction'),
(4, 'Biography'),
(5, 'History');


INSERT INTO Book (book_id, title, author, availability, book_category_id) VALUES
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 1, 1),
(2, '1984', 'George Orwell', 0, 3),
(3, 'Sapiens', 'Yuval Noah Harari', 1, 2),
(4, 'Becoming', 'Michelle Obama', 1, 4),
(5, 'A Brief History of Time', 'Stephen Hawking', 1, 3),
(6, 'Team of Rivals', 'Doris Kearns Goodwin', 0, 5),
(7, 'To Kill a Mockingbird', 'Harper Lee', 1, 1),
(8, 'The Wright Brothers', 'David McCullough', 1, 4);
