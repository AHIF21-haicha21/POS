package at.htlkaindorf.springextended.repository;

import at.htlkaindorf.springextended.pojos.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
