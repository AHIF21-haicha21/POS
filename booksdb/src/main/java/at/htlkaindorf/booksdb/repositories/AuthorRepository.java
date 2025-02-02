package at.htlkaindorf.booksdb.repositories;

import at.htlkaindorf.booksdb.pojos.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
