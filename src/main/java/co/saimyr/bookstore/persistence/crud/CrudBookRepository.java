package co.saimyr.bookstore.persistence.crud;

import java.util.List;

import co.saimyr.bookstore.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CrudBookRepository extends JpaRepository<BookEntity, Integer> {
	public List<BookEntity> findByAuthor(String author);
	public List<BookEntity> findAllByPublisher(String author);
	public BookEntity findByIsbn(int isbn);

}
