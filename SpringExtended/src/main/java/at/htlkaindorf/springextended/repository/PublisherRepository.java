package at.htlkaindorf.springextended.repository;

import at.htlkaindorf.springextended.pojos.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
