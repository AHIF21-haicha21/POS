package at.htlkaindorf.jpa_classinfo.repositories;

import at.htlkaindorf.jpa_classinfo.pojos.ClassTeacher;
import at.htlkaindorf.jpa_classinfo.pojos.Floor;
import at.htlkaindorf.jpa_classinfo.pojos.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Long> {
    @Query("SELECT c FROM ClassTeacher c WHERE c.firstname || ' ' || c.lastname LIKE ?1")
    public ClassTeacher findByName(String name);

    @Query("SELECT c FROM ClassTeacher c WHERE c.hTLClass.name = ?1")
    public List<ClassTeacher> findByClassname(String classname);

    @Query("SELECT c FROM ClassTeacher c WHERE c.hTLClass.grade = ?1")
    public List<ClassTeacher> findByGrade(Integer grade);

    @Query("SELECT COUNT(c) FROM ClassTeacher c")
    public Integer countAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM ClassTeacher c WHERE c.firstname = ?1 AND c.lastname = ?2")
    public List<ClassTeacher> deleteClassTeacherByFirstnameAndLastname(String firstname, String lastname);
}
