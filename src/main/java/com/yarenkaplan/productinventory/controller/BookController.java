package com.yarenkaplan.productinventory.controller;

import com.yarenkaplan.productinventory.dto.BookResponseDTO;
import com.yarenkaplan.productinventory.entity.Book;
import com.yarenkaplan.productinventory.errors.BookNotFoundException;
import com.yarenkaplan.productinventory.repository.BookRepository;
import com.yarenkaplan.productinventory.requests.book.CreateBookRequest;
import com.yarenkaplan.productinventory.requests.book.UpdateBookRequest;
import com.yarenkaplan.productinventory.services.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/createBook")
    public void createBook(@RequestBody CreateBookRequest createBookRequest) {
        bookService.createBook(createBookRequest);
    }

    @PutMapping("/updateBook/{id}")
    public void updateBook(@RequestBody UpdateBookRequest updateBookRequest, @PathVariable Long id) {
        bookService.updateBook(updateBookRequest, id);
    }

    @GetMapping("/findAllBooks")
    public List<BookResponseDTO> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/findBookById/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @DeleteMapping("/deleteAllBooks")
    public void deleteAllBooks() {
        bookService.deleteAllBooks();
    }
}
