package at.htlkaindorf.eventmanagement.repository;

import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Location;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {

//    List<Event> findByDateAfter(LocalDate date);
//
//    @Query("SELECT e FROM Event e WHERE e.location.capacity - e.participants.size > :count")
//    List<Event> findByFreeTicketCount(Integer count);

    List<Event> findByIdIn(Collection<String> id);

    @Query("SELECT COUNT(*) > 0 FROM Event e JOIN e.participants p WHERE e = :event AND p = :participant")
    boolean participatesInEvent(Event event, Participant participant);
}
