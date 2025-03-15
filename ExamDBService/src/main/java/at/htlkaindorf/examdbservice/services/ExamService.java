package at.htlkaindorf.examdbservice.services;

import at.htlkaindorf.examdbservice.database.ExamMockDB;
import at.htlkaindorf.examdbservice.pojos.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExamService {
    private final ExamMockDB db;

    public Optional<Exam> deleteExam(Long studentId, Long examId) {
        return db.deleteExamById(studentId, examId);
    }

    public Optional<Exam> addExamToStudent(Long studentId, Exam exam) {
        return db.addExamToStudent(studentId, exam);
    }
}
