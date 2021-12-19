package co.saimyr.bookstore.persistence;

import java.util.List;
import co.saimyr.bookstore.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.saimyr.bookstore.domain.repository.BookRepository;
import co.saimyr.bookstore.persistence.crud.CrudBookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
	@Autowired
	private CrudBookRepository h2BookRepo;

	@Override
	public List<BookEntity> findAll() {
		return (List<BookEntity>) h2BookRepo.findAll();
	}
	
	@Override
	public List<BookEntity> findByAuthor(String author) {
		return h2BookRepo.findByAuthor(author);
	}

	@Override
	public List<BookEntity> findAllByPublisher(String publisher) {
		return h2BookRepo.findAllByPublisher(publisher);
	}

	@Override
	public void delete(BookEntity book) {
		h2BookRepo.delete(book);
	}

	@Override
	public BookEntity findByIsbn(int isbn) {
		return h2BookRepo.findByIsbn(isbn);
	}

	@Override
	public BookEntity save(BookEntity b) {
		return h2BookRepo.save(b);
	}


}
