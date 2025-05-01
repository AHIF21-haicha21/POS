package at.htlkaindorf.springextended.repository;

import at.htlkaindorf.springextended.pojos.Author;
import at.htlkaindorf.springextended.pojos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Named Queries

    @Query(name = "Book.findByTitle")
    List<Book> findByTitleNamed(@Param("title") String title);


    // Derived Queries (kann Pageable einfach dazugeben)

    // Find all books by a defined title
    List<Book> findByTitle(String title);

    // Find all books by one defined author name
    List<Book> findByAuthor_Name(String name);

    // Find all books from one author and publisher

    List<Book> findByAuthor_NameAndPublisher_Name(String authorName, String publisherName);

    // Hard to read when they get complex, no grouping at all

    // Query Methods ( JPQL Queries)

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    List<Book> findBooksByTitle(String title);

    // Find all books by one defined author name
    @Query("SELECT b FROM Book b WHERE b.author.name LIKE :name")
    List<Book> findBooksByAuthor_Name(String name);

    // Find all books from one author and publisher
    @Query("SELECT b FROM Book b WHERE b.author.name LIKE :authorName AND b.publisher.name LIKE :publisherName")
    List<Book> findBooksByAuthorPublisher(String authorName, String publisherName);

    @Query("SELECT b FROM Book b WHERE b.author IN :authors")
    List<Book> findBooksByAuthors(List<Author> authors);

    @Query("SELECT b.author.name, COUNT(*) FROM Book b GROUP BY b.author.name HAVING b.author.name LIKE :name")
    int findNumberOfBooksPerAuhtor(String name);

    // Native Query

    @Query(nativeQuery = true, value = "SELECT b.* FROM book b INNER JOIN author a ON a.id = b.author WHERE a.name LIKE :name")
    List<Book> findBooksByAuthorName(String name);
}
