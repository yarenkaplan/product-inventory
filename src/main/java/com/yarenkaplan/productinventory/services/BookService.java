package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.BookResponseDTO;
import com.yarenkaplan.productinventory.entity.Book;
import com.yarenkaplan.productinventory.requests.book.CreateBookRequest;
import com.yarenkaplan.productinventory.requests.book.UpdateBookRequest;

import java.util.List;

public interface BookService {

    //CRUD
    void createBook(CreateBookRequest createBookRequest);
    void updateBook(UpdateBookRequest updateBookRequest, Long id);
    List<BookResponseDTO> findAllBooks();
    Book findBookById(Long id);
    void deleteBookById(Long id);
    void deleteAllBooks();

}
