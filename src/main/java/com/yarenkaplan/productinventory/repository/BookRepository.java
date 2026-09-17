package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
