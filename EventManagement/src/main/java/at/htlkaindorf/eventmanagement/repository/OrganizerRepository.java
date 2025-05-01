package at.htlkaindorf.eventmanagement.repository;

import at.htlkaindorf.eventmanagement.pojos.Location;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

//    @Query("SELECT o FROM Organizer o WHERE 5 < (SELECT COUNT(*) FROM Event e WHERE e.location = :location)")
//    List<Organizer> findByPracticeAtLocation(Location location);
}
