package kodluyoruz.project.service;

import kodluyoruz.project.model.Book;
import kodluyoruz.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        Optional<Book> currentBook = bookRepository.findById(id);
        if (currentBook.isPresent()) {
            return currentBook.get();
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        long id = book.getId();
        Optional<Book> currentBook = bookRepository.findById(id);
        if (currentBook.isPresent()) {
            Book updatedBook = currentBook.get();
            updatedBook.setName(book.getName());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setPage(book.getPage());
            bookRepository.save(updatedBook);
        }
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        Optional<Book> currentBook = bookRepository.findById(id);
        if (currentBook.isPresent()) {
            bookRepository.deleteById(id);
        }
    }

    @Override
    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }





}