package at.htlkaindorf.springextended.repository;

import at.htlkaindorf.springextended.pojos.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
