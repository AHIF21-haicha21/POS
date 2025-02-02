package at.htlkaindorf.jpa_classinfo.repositories;

import at.htlkaindorf.jpa_classinfo.pojos.Floor;
import at.htlkaindorf.jpa_classinfo.pojos.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomReposiroty extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.htlClass.name = ?1")
    public Room findByClassName(String name);

    @Query("SELECT r FROM Room r")
    public List<Room> findAll();

    @Query("SELECT r FROM Room r WHERE r.htlClass.room.floor = ?1")
    public List<Room> findByFloor(Floor floor);

    @Query("SELECT COUNT(r) FROM Room r")
    public Integer countAll();
}
