package at.htlkaindorf.eventmanagement.repository;

import at.htlkaindorf.eventmanagement.pojos.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

//    List<Location> findByEvents_maxParticipantsGreatThan(Integer count);
}
