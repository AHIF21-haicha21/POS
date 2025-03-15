package at.htlkaindorf.examdbservice.json;

import at.htlkaindorf.examdbservice.pojos.Classname;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class JSON_Access {
    public static List<Classname> read() throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        InputStream is = JSON_Access.class.getResourceAsStream("/examdb.json");

        List<Classname> classnames = mapper.readerForListOf(Classname.class).readValue(is);

        AtomicLong idCounter = new AtomicLong();
        classnames.stream()
                .flatMap(s -> s.getStudents().stream())
                .forEachOrdered(s -> s.setStudentId(idCounter.getAndIncrement()));

        return classnames;
    }
}
