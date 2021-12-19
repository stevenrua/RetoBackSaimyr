package co.saimyr.bookstore.BooksControllerTest;
import co.saimyr.bookstore.domain.repository.BookRepository;
import co.saimyr.bookstore.domain.service.BookService;
import co.saimyr.bookstore.persistence.entity.BookEntity;
import co.saimyr.bookstore.web.controller.BookController;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

class BooksControllerTest {
    @Autowired
    private final BookRepository bookRepositoryMock =Mockito.mock(BookRepository.class);

    @Autowired
    private final BookService bookService =new BookService(bookRepositoryMock);

    @Autowired
    private final BookController bookController =new BookController(bookService);

    @Test
    void testGetListBooks(){
        List<BookEntity> booksList =new ArrayList<>();
        booksList.add(new BookEntity(1, "Akellare", "Mario Mendoza", "Planeta", "Fiction"));
        booksList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));
        booksList.add(new BookEntity(3, "El olvido que seremos", "Hector Abad Faciolince", "Planeta", "Biography"));

        Mockito.when(bookRepositoryMock.findAll()).thenReturn(booksList);

        ResponseEntity<List<BookEntity>> serviceResponse;
        serviceResponse =bookController.getAll();

        List<BookEntity>expectedBookList = new ArrayList<>();
        expectedBookList.add(new BookEntity(1, "Akellare", "Mario Mendoza", "Planeta", "Fiction"));
        expectedBookList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));
        expectedBookList.add(new BookEntity(3, "El olvido que seremos", "Hector Abad Faciolince", "Planeta", "Biography"));

        Assertions.assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBookList, serviceResponse.getBody());
    }

    @Test
    void testGetListBooksVoid(){
        List<BookEntity>booksList = new ArrayList<>();
        Mockito.when(bookRepositoryMock.findAll()).thenReturn(booksList);

        ResponseEntity<List<BookEntity>> serviceResponse;
        serviceResponse = bookController.getAll();

        List<BookEntity>expectedBooksList = new ArrayList<>();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBooksList, serviceResponse.getBody());

    }

    @Test
    void testGetListBooksByAuthor(){
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));

        Mockito.when(bookRepositoryMock.findByAuthor("Elvira Sastre")).thenReturn(bookList);

        ResponseEntity<List<BookEntity>> serviceResponse;
        serviceResponse = bookController.getByAuthor("Elvira Sastre");

        List<BookEntity>expectedBooksList = new ArrayList<>();
        expectedBooksList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));

        Assertions.assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBooksList, serviceResponse.getBody());
    }

    @Test
    void testGetListBooksByAuthorNoFound(){
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));

        Mockito.when(bookRepositoryMock.findByAuthor("Elvira Sastre")).thenReturn(bookList);

        ResponseEntity<List<BookEntity>> serviceResponse;
        serviceResponse = bookController.getByAuthor("Elvi Sastre");

        List<BookEntity>expectedBooksList = new ArrayList<>();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBooksList, serviceResponse.getBody());
    }

    @Test
    void testGetListBooksByPublisher(){
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));

        Mockito.when(bookRepositoryMock.findAllByPublisher("Seis Barral")).thenReturn(bookList);

        ResponseEntity<List<BookEntity>> serviceResponse;
        serviceResponse = bookController.getByPublisher("Seis Barral");

        List<BookEntity>expectedBooksList = new ArrayList<>();
        expectedBooksList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));

        Assertions.assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBooksList, serviceResponse.getBody());
    }

    @Test
    void testGetListBooksByPublisherNotFound(){
        List<BookEntity> bookList = new ArrayList<>();
        bookList.add(new BookEntity(2, "Días sin ti", "Elvira Sastre", "Seis Barral", "novel"));

        Mockito.when(bookRepositoryMock.findAllByPublisher("Seis Barral")).thenReturn(bookList);

        ResponseEntity<List<BookEntity>> serviceResponse;
        serviceResponse = bookController.getByPublisher("6 Barral");

        List<BookEntity>expectedBooksList = new ArrayList<>();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBooksList, serviceResponse.getBody());
    }

    @Test
    void testNewBook(){
        BookEntity newBook = new BookEntity(4, "Cien años de soledad", "Gabriel Garcia", "Sudamericana", "Novel");

        Mockito.when(bookRepositoryMock.save(newBook)).thenReturn(newBook);

        ResponseEntity<BookEntity> serviceResponse;
        serviceResponse = bookController.newBook(newBook);

        BookEntity expectedBook = new BookEntity(4, "Cien años de soledad", "Gabriel Garcia", "Sudamericana", "Novel");

        Assertions.assertEquals(HttpStatus.CREATED, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBook, serviceResponse.getBody());
    }

    @Test
    void testDelete(){
        BookEntity deleteBook =new BookEntity(4, "Cien años de soledad", "Gabriel Garcia", "Sudamericana", "Novel");

        Mockito.when(bookRepositoryMock.findByIsbn(deleteBook.getIsbn())).thenReturn(deleteBook);

        ResponseEntity serviceResponse;
        serviceResponse = bookController.deleteByIsbn(deleteBook.getIsbn());

        BookEntity expectedBook = new BookEntity();

        Assertions.assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBook, serviceResponse.getBody());
    }

    @Test
    void testDeleteNotFound(){
        BookEntity deleteBook =new BookEntity(4, "Cien años de soledad", "Gabriel Garcia", "Sudamericana", "Novel");

        Mockito.when(bookRepositoryMock.findByIsbn(deleteBook.getIsbn())).thenReturn(null);

        ResponseEntity serviceResponse;
        serviceResponse = bookController.deleteByIsbn(deleteBook.getIsbn());

        BookEntity expectedBook = new BookEntity();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, serviceResponse.getStatusCode());
        Assertions.assertEquals(expectedBook, serviceResponse.getBody());
    }

}
