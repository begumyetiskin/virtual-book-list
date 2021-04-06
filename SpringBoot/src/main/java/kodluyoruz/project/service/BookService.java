package kodluyoruz.project.service;

import kodluyoruz.project.model.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
    void deleteAllBooks();
}

