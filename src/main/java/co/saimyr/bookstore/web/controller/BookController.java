package co.saimyr.bookstore.web.controller;

import java.util.List;
import co.saimyr.bookstore.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import co.saimyr.bookstore.domain.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BookController {
	@Autowired
	private BookService bookService;

	public BookController(BookService bookService){
		this.bookService = bookService;
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BookEntity>> getAll() {
		return bookService.getAll();
	}
	
	@GetMapping(value = "/author/{author}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BookEntity>> getByAuthor(@PathVariable("author") String author) {
		return bookService.getAllByAuthor(author);
	}

	@GetMapping(value = "/publisher/{publisher}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BookEntity>> getByPublisher(@PathVariable("publisher") String publisher) {
		return bookService.getAllByPublisher(publisher);
	}

	@PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BookEntity> newBook(@RequestBody BookEntity book) {
		return bookService.newBook(book);
	}

	@DeleteMapping(value = "/delete/{isbn}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BookEntity> deleteByIsbn(@PathVariable("isbn") int isbn) {
		return bookService.delete(isbn);
	}

}
