package at.htlkaindorf.examdbservice.web;

import at.htlkaindorf.examdbservice.pojos.Classname;
import at.htlkaindorf.examdbservice.pojos.Student;
import at.htlkaindorf.examdbservice.services.ClassnameService;
import at.htlkaindorf.examdbservice.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final ClassnameService classnameService;

    @GetMapping("all")
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllOfClass(@RequestParam(name = "class") String classname, @RequestParam(name = "orderBy", required = false) String orderBy) {
        if (orderBy != null && !(orderBy.equals("firstname") || orderBy.equals("lastname")))
            return ResponseEntity.badRequest().build();

        Optional<Classname> cn = classnameService.getClassnameByName(classname);
        if (cn.isEmpty())
            return ResponseEntity.notFound().build();

        List<Student> students = cn.get().getStudents();
        if (orderBy != null)
            students.sort(Comparator.comparing(orderBy.equals("firstname") ? Student::getFirstname : Student::getLastname));

        return ResponseEntity.ok(students);
    }

    @PatchMapping("{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        return ResponseEntity.of(studentService.updateStudent(studentId, student));
    }
}

