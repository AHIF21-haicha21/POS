package at.htlkaindorf.examdb.web;

import at.htlkaindorf.examdb.pojos.Student;
import at.htlkaindorf.examdb.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/examdb/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping("/all")
    private ResponseEntity<List<Student>> getStudentsByClassnameAndDuration(
            @RequestParam(name = "classname") String className,
            @RequestParam(name = "duration") Integer duration
            ) {
        return ResponseEntity.ok(studentRepository.getStudentByClassnameAndDuration(className, duration));
    }

    @GetMapping("/count")
    private ResponseEntity<Integer> countStudentsWithinExamPeriod(
            @RequestParam(name = "start") @DateTimeFormat(pattern = "ddMMyyyy") LocalDate start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "ddMMyyyy") LocalDate end
    ) {
        return ResponseEntity.ok(studentRepository.countStudentsWithinExamPeriod(start, end));
    }
}
