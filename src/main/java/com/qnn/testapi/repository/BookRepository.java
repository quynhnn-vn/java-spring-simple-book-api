package com.qnn.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qnn.testapi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
}
