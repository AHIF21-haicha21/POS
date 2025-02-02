package at.htlkaindorf.booksdb.repositories;

import at.htlkaindorf.booksdb.pojos.Author;
import at.htlkaindorf.booksdb.pojos.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    @Query("SELECT a FROM Publisher p JOIN p.books b JOIN b.authors a WHERE p.name = ?1")
    public List<Author> getAllAuthorsOfPublisher(String publisherName);

    @Query("SELECT a FROM Publisher p JOIN p.books b JOIN b.authors a GROUP BY a HAVING COUNT(*) >= ?1")
    public List<Author> getAuthorsWithAtLeastNumberBooks(Integer number);
}
