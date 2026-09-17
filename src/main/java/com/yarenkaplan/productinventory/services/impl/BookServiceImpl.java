package com.yarenkaplan.productinventory.services.impl;

import com.yarenkaplan.productinventory.dto.BookResponseDTO;
import com.yarenkaplan.productinventory.entity.Book;
import com.yarenkaplan.productinventory.errors.BookNotFoundException;
import com.yarenkaplan.productinventory.repository.BookRepository;
import com.yarenkaplan.productinventory.requests.book.CreateBookRequest;
import com.yarenkaplan.productinventory.requests.book.UpdateBookRequest;
import com.yarenkaplan.productinventory.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(CreateBookRequest createBookRequest) {
        Book book = new Book();
        book.setTitle(createBookRequest.getTitle());
        book.setAuthor(createBookRequest.getAuthor());
        book.setIsbn(createBookRequest.getIsbn());
        book.setStock(createBookRequest.getStock());
        book.setPrice(createBookRequest.getPrice());

        bookRepository.save(book);
    }

    @Override
    public void updateBook(UpdateBookRequest updateBookRequest, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if (!book.getTitle().equals(updateBookRequest.getTitle()) && (updateBookRequest.getTitle() != null) && !(updateBookRequest.getTitle().trim().isBlank())) {
            book.setTitle(updateBookRequest.getTitle());
        }

        if (!book.getAuthor().equals(updateBookRequest.getAuthor()) && (updateBookRequest.getAuthor() != null) && !(updateBookRequest.getAuthor().trim().isBlank())) {
            book.setAuthor(updateBookRequest.getAuthor());
        }

        if (!book.getIsbn().equals(updateBookRequest.getIsbn()) && (updateBookRequest.getIsbn() != null)) {
            book.setIsbn(updateBookRequest.getIsbn());
        }

        if (!book.getStock().equals(updateBookRequest.getStock()) && (updateBookRequest.getStock() != null)) {
            book.setStock(updateBookRequest.getStock());
        }

        if (!book.getPrice().equals(updateBookRequest.getPrice()) && (updateBookRequest.getPrice() != null)) {
            book.setPrice(updateBookRequest.getPrice());
        }

        bookRepository.save(book);
    }

    @Override
    public List<BookResponseDTO> findAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    private BookResponseDTO mapToResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();

        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setIsbn(book.getIsbn());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setStock(book.getStock());

        return bookResponseDTO;
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
