package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bRepo;

	public void save(Book book) {
		bRepo.save(book);
	}

	public List<Book> getAllBooks() {
		return bRepo.findAll();
	}

	public Book getBookById(int id) {
		return bRepo.findById(id).orElse(null);
	}

	public void deleteById(int id) {
		bRepo.deleteById(id);
	}

	public List<Book> searchBooksByName(String name) {
		System.out.println("Searching for books with name: " + name);
		return bRepo.findByNameContainingIgnoreCase(name);
	}
}
