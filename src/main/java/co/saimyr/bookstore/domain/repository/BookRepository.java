package co.saimyr.bookstore.domain.repository;

import java.util.List;
import co.saimyr.bookstore.persistence.entity.BookEntity;

public interface BookRepository {
	List<BookEntity> findAll();
	List<BookEntity> findByAuthor(String author);
	List<BookEntity> findAllByPublisher(String publisher);
	void delete(BookEntity book);
	BookEntity findByIsbn(int isbn);
	BookEntity save(BookEntity b);

}
