package co.saimyr.bookstore.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BOOKS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookEntity implements Comparable<BookEntity>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isbn;
	private String name;
	private String author;
	private String publisher;
	private String genre;

	@Override
	public int compareTo(BookEntity book) {
		return name.compareTo(book.getName());
	}
}
