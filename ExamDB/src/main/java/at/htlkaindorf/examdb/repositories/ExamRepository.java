package at.htlkaindorf.examdb.repositories;

import at.htlkaindorf.examdb.pojos.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
