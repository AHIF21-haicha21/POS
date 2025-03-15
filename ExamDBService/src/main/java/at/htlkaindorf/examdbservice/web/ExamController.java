package at.htlkaindorf.examdbservice.web;

import at.htlkaindorf.examdbservice.pojos.Exam;
import at.htlkaindorf.examdbservice.pojos.Student;
import at.htlkaindorf.examdbservice.services.ExamService;
import at.htlkaindorf.examdbservice.services.StudentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class ExamController {
    private final StudentService studentService;
    private final ExamService examService;

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAllExamsOfStudent(@RequestParam(required = true) Long studentId) {
        Optional<Student> student = studentService.getStudentWithId(studentId);

        if (student.isEmpty())
            return ResponseEntity.notFound().build();
        List<Exam> exams = student.get().getExams();

        return ResponseEntity.ok(exams);
    }

    @DeleteMapping("/exam/{studentId}/{examId}")
    public ResponseEntity<Exam> deleteExam(@PathVariable Long studentId, @PathVariable Long examId) {
        return ResponseEntity.of(examService.deleteExam(studentId, examId));
    }

    @PostMapping("/exam/{studentId}")
    private ResponseEntity<Exam> addExamToStudent(@PathVariable Long studentId, @RequestBody Exam exam) {
        return ResponseEntity.of(examService.addExamToStudent(studentId, exam));
    }
}
