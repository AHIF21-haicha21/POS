package at.htlkaindorf.jpa_classinfo.web;

import at.htlkaindorf.jpa_classinfo.pojos.ClassTeacher;
import at.htlkaindorf.jpa_classinfo.repositories.ClassTeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/schule/lehrpersonal")
public class HTLController {
    private final ClassTeacherRepository classTeacherRepository;

    @DeleteMapping("/kuendigen")
    public ResponseEntity<List<ClassTeacher>> fireFarmer(
            @RequestParam(name="firstName") String firstName,
            @RequestParam(name="lastName") String lastName
    ) {

        List<ClassTeacher> deletedTeachers = classTeacherRepository.deleteClassTeacherByFirstnameAndLastname(firstName, lastName);

        return ResponseEntity.ok(deletedTeachers);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClassTeacher>> getAll() {
        return ResponseEntity.ok(classTeacherRepository.findAll());
    }
}
