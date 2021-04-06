package kodluyoruz.project.controller;

import kodluyoruz.project.model.Book;
import kodluyoruz.project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/books", produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping(path = "/books/{id}", produces = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping(path = "/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping(path = "/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable(value = "id") Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book with id: " + id + " is deleted.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/books")
    public ResponseEntity<String> deleteAllBook()  {
        bookService.deleteAllBooks();
        return new ResponseEntity<>("All book is deleted.", HttpStatus.OK);
    }
}