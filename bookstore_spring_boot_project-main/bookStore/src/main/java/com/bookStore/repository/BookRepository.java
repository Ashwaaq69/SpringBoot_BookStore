package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>  {
        List<Book> findByNameContainingIgnoreCase(String name);

}

