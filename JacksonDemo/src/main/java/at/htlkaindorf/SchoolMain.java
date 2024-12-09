package at.htlkaindorf;

import at.htlkaindorf.pojos.School;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;
public class SchoolMain {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Register JTM to (de)serialize dates
        mapper.registerModule(new JavaTimeModule());
        List<School> schools = mapper.readValue(SchoolMain.class.getResource("/students.json"), new TypeReference<List<School>>() {
        });


        for (School school : schools
        ) {
            System.out.println(school);

        }
    }


}
