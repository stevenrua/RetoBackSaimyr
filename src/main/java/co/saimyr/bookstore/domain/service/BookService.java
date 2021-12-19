package co.saimyr.bookstore.domain.service;

import java.util.Collections;
import java.util.List;

import co.saimyr.bookstore.persistence.entity.BookEntity;
import co.saimyr.bookstore.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	private List<BookEntity> listBook;

	public BookService(BookRepository bookRepository){
		this.bookRepository = bookRepository;
	}

	public ResponseEntity<List<BookEntity>> getAll() {
		listBook = bookRepository.findAll();
		if(listBook.isEmpty()){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(listBook);
		}
		return ResponseEntity.status(HttpStatus.OK).body(orderList(listBook));
	}
	public ResponseEntity<List<BookEntity>> getAllByAuthor(String author) {
		listBook = bookRepository.findByAuthor(author);
		if(listBook.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listBook);
		}
		return ResponseEntity.status(HttpStatus.OK).body(orderList(listBook));
	}
	public ResponseEntity<List<BookEntity>> getAllByPublisher(String publisher) {
		listBook = bookRepository.findAllByPublisher(publisher);
		if(listBook.isEmpty()){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(listBook);
		}
		return ResponseEntity.status(HttpStatus.OK).body(orderList(listBook));
	}
	public ResponseEntity<BookEntity> newBook(BookEntity book) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
	}
	public ResponseEntity<BookEntity> delete(int isbn){
		BookEntity deleteBook = bookRepository.findByIsbn(isbn);
		if(deleteBook != null){
			bookRepository.delete(deleteBook);
			return ResponseEntity.status(HttpStatus.OK).body(new BookEntity());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookEntity());
	}

	//Metodo para ordenar, es llamado cuando se listan todos los libros,
	//Cuando se listan por autor y por editorial
	private List<BookEntity> orderList(List naturalList){
		List<BookEntity> ListOrder = naturalList;
		Collections.sort(ListOrder);
		return ListOrder;
	}
}
