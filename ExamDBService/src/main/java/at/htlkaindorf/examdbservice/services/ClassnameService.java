package at.htlkaindorf.examdbservice.services;

import at.htlkaindorf.examdbservice.database.ExamMockDB;
import at.htlkaindorf.examdbservice.pojos.Classname;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClassnameService {
    private final ExamMockDB db;

    public List<Classname> getAll() {
        return db.getAllClassnames();
    }

    public Optional<Classname> getClassnameByName(String classname) {
        return db.getClassnameByName(classname);
    }
}
