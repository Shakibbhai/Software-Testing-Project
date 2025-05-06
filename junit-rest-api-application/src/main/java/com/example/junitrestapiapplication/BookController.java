package com.example.junitrestapiapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllbookRecords(){
    return bookRepository.findAll(); }

    @GetMapping(value="{bookId}")
    public Book getBookId(@PathVariable(value = "bookId") Long bookId){
        return bookRepository.findById(bookId).get();

    }

    @PostMapping
    public Book createBookRecord(@RequestBody Book bookRecord){
    return bookRepository.save(bookRecord);
    }
    @PutMapping
    public Book updateBookRecord(@RequestBody Book bookRecord) {
        if (bookRecord == null || bookRecord.getBookId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book record or ID must not be null");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookRecord.getBookId());
        if (!optionalBook.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with ID: " + bookRecord.getBookId() + " does not exist");
        }

        Book existBookRecord = optionalBook.get();
        existBookRecord.setName(bookRecord.getName());
        existBookRecord.setSummary(bookRecord.getSummary());
        existBookRecord.setRating(bookRecord.getRating());

        return bookRepository.save(existBookRecord);
    }
    @DeleteMapping(value = "{bookId}")
    public void deleteBookById(@PathVariable(value = "bookId") Long bookId) {
        if (!bookRepository.findById(bookId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with ID " + bookId + " does not exist.");
        }
        bookRepository.deleteById(bookId);
    }

}
