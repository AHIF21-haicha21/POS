package at.htlkaindorf.examdb.database;

import at.htlkaindorf.examdb.pojos.Classname;
import at.htlkaindorf.examdb.repositories.ClassnameRepository;
import at.htlkaindorf.examdb.repositories.ExamRepository;
import at.htlkaindorf.examdb.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseInitializer {
    private final ClassnameRepository classnameRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    @PostConstruct
    private void initDatabase() {
        final String dataSourceFilename = "examdb.json";

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            List<Classname> classnames = mapper.readerForListOf(Classname.class).readValue(this.getClass().getResourceAsStream("/" + dataSourceFilename));

            classnameRepository.saveAll(classnames);
        } catch (IOException e) {
            log.error(String.format("Error while reading %s", dataSourceFilename));
            throw new RuntimeException(e);
        }

    }
}
