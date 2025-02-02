package at.htlkaindorf.jpa_classinfo.repositories;

import at.htlkaindorf.jpa_classinfo.pojos.Floor;
import at.htlkaindorf.jpa_classinfo.pojos.HTLClass;
import at.htlkaindorf.jpa_classinfo.pojos.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HTLClassRepository extends JpaRepository<HTLClass, Long> {
    @Query("SELECT c FROM HTLClass c WHERE c.name = ?1")
    public HTLClass findByName(String name);

    @Query("SELECT c FROM HTLClass c")
    public List<HTLClass> findAll();

    @Query("SELECT c FROM HTLClass c WHERE c.room.floor = ?1")
    public List<HTLClass> findByFloor(Floor floor);

    @Query("SELECT COUNT(c) FROM HTLClass c")
    public Integer countAll();
}
