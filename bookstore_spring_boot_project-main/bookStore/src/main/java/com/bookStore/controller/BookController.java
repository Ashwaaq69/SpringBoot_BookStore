package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

import java.util.List;

@Controller
public class BookController {

	@Autowired
	private BookService service;

	@Autowired
	private MyBookListService myBookService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
		List<Book> list = service.getAllBooks();
		return new ModelAndView("bookList", "book", list);
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book) {
		service.save(book);
		return "redirect:/available_books";
	}

	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list = myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@RequestMapping("/mylist/{id}")
	public String addToMyList(@PathVariable("id") int id) {
		Book book = service.getBookById(id);
		MyBookList myBook = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
		myBookService.saveMyBooks(myBook);
		return "redirect:/my_books";
	}

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = service.getBookById(id);
		model.addAttribute("book", book);
		return "bookEdit";
	}

	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}

	@GetMapping("/search")
	public String searchBooks(@RequestParam("query") String query, Model model) {
		System.out.println("Search query: " + query);
		List<Book> list = service.searchBooksByName(query);
		model.addAttribute("book", list);
		return "bookList";
	}
}
