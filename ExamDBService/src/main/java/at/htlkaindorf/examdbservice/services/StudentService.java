package at.htlkaindorf.examdbservice.services;

import at.htlkaindorf.examdbservice.annotations.Unpatchable;
import at.htlkaindorf.examdbservice.database.ExamMockDB;
import at.htlkaindorf.examdbservice.pojos.Classname;
import at.htlkaindorf.examdbservice.pojos.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.javapoet.ClassName;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentService {
    private final ExamMockDB db;

    public Optional<Student> getStudentWithId(Long id) {
        return db.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return db.getAllStudents();
    }

    public Optional<Student> updateStudent(Long studentId, Student newStudent) {
        Optional<Student> student = db.getStudentById(studentId);

        if(student.isEmpty())
            return Optional.empty();

        Field[] fields = Student.class.getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Unpatchable.class))
                continue;

            try {
                Object v = field.get(newStudent);
                if(v != null)
                    field.set(student.get(), v);
            } catch (IllegalAccessException e) {
                log.error(String.format("%s is not accessible", field.getName()));
            }
        }

        return student;
    }
}
