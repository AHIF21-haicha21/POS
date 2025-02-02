package at.htlkaindorf.examdb.repositories;

import at.htlkaindorf.examdb.pojos.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s " +
            "FROM Student s " +
            "JOIN s.exams e " +
            "WHERE s.classname.className = ?1 " +
            "AND e.duration = ?2")
    public List<Student> getStudentByClassnameAndDuration(String classname, Integer duration);

    @Query("SELECT COUNT(*) " +
            "FROM Student s " +
            "JOIN s.exams e " +
            "WHERE e.dateOfExam BETWEEN ?1 AND ?2")
    public Integer countStudentsWithinExamPeriod(LocalDate start, LocalDate end);


}
