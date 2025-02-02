package at.htlkaindorf.booksdb.repositories;

import at.htlkaindorf.booksdb.pojos.Author;
import at.htlkaindorf.booksdb.pojos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.price > (SELECT MAX(b2.price) FROM Book b2 WHERE b2.publisher.name = :publisherName)")
    List<Book> getBooksWithPriceOverMaxPriceOfPublisher(@Param("publisherName") String publisherName);
}
