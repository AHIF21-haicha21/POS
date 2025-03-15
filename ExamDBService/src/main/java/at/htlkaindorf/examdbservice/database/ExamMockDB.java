package at.htlkaindorf.examdbservice.database;

import at.htlkaindorf.examdbservice.json.JSON_Access;
import at.htlkaindorf.examdbservice.pojos.Classname;
import at.htlkaindorf.examdbservice.pojos.Exam;
import at.htlkaindorf.examdbservice.pojos.Student;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ExamMockDB {
    private List<Student> students = new ArrayList<>();
    private List<Exam> exams = new ArrayList<>();
    private List<Classname> classnames = new ArrayList<>();

    @PostConstruct
    private void loadData() {
        try {
            this.classnames = JSON_Access.read();
            this.students = this.classnames.stream().flatMap(cn -> cn.getStudents().stream()).collect(Collectors.toList());
            this.exams = this.students.stream().flatMap(cn -> cn.getExams().stream()).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error while reading data!");
            throw new RuntimeException(e);
        }
    }

    public Optional<Student> getStudentById(Long id) {
        return this.students.stream().filter(s -> Objects.equals(s.getStudentId(), id)).findFirst();
    }

    public Optional<Exam> deleteExamById(Long studentId, Long examId){
        Optional<Student> student = this.students.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst();

        if(student.isEmpty())
            return Optional.empty();

        Optional<Exam> exam = student.get().getExams().stream().filter(e -> e.getExamId().equals(examId)).findFirst();

        if(exam.isEmpty())
            return exam;

        this.exams.remove(exam.get());
        student.get().getExams().remove(exam.get());

        return exam;
    }

    public Optional<Exam> addExamToStudent(Long studentId, Exam exam){
        Optional<Student> student = this.students.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst();

        if(student.isEmpty())
            return Optional.empty();
        exam.setStudent(student.get());

        Long highestExamId = student.get().getExams().stream().map(Exam::getExamId).max(Long::compareTo).orElse(-1L);
        exam.setExamId(highestExamId + 1);


        this.exams.remove(exam);
        student.get().getExams().add(exam);

        return Optional.of(exam);
    }

    public List<Classname> getAllClassnames() {
        return this.classnames;
    }

    public List<Student> getAllStudents() {
        return this.students;
    }

    public Optional<Classname> getClassnameByName(String name){
        return classnames.stream().filter(cn -> cn.getClassname().equals(name)).findFirst();
    }
}
