package at.htlkaindorf.eventmanagement.repository;

import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    // TROLOLOLO-Derived Query (Erkennen Schüler das ?!!!?11?!)
    // List<Participant> findParticipantByEventsCount(Integer count);

//    @Query("SELECT p " +
//            "FROM Participant p " +
//            "WHERE p.events.size > :count")
//    List<Participant> findByEventsRegistered(Integer count);
//
//    @Query("SELECT p " +
//            "FROM Participant p " +
//            "JOIN Event e " +
//            "WHERE e.date < CURRENT_DATE + 7 " +
//            "GROUP BY p " +
//            "HAVING COUNT(*) > 5")
//    List<Participant> findByAttendanceNextWeek();

    List<Participant> findParticipantByIdIn(Collection<Long> ids);
}
