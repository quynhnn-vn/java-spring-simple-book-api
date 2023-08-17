package com.qnn.testapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qnn.testapi.exception.BookNotFoundException;
import com.qnn.testapi.model.Book;
import com.qnn.testapi.repository.BookRepository;

import jakarta.validation.Valid;

// @RestController: denote all methods as Domain Object
@RestController
public class BookController {
    // @Autowired: wire the bean classes automatically
    @Autowired
    BookRepository bookRepository;

    // Get all books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a single book
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    // Create a new book
    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    // Update a book
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book bookDetails) throws BookNotFoundException {
        Book matchedBook = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        matchedBook.setBook_name(bookDetails.getBook_name());
        matchedBook.setAuthor_name(bookDetails.getAuthor_name());
        matchedBook.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(matchedBook);
    } 

    // Delete a book
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        Book matchedBook = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(matchedBook);
        return ResponseEntity.ok().build();
    }
}
