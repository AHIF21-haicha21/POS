package at.htlkaindorf.jpa_classinfo.database;

import at.htlkaindorf.jpa_classinfo.pojos.ClassTeacher;
import at.htlkaindorf.jpa_classinfo.pojos.Floor;
import at.htlkaindorf.jpa_classinfo.pojos.HTLClass;
import at.htlkaindorf.jpa_classinfo.pojos.Room;
import at.htlkaindorf.jpa_classinfo.repositories.ClassTeacherRepository;
import at.htlkaindorf.jpa_classinfo.repositories.HTLClassRepository;
import at.htlkaindorf.jpa_classinfo.repositories.RoomReposiroty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private final ClassTeacherRepository classTeacherRepository;
    private final RoomReposiroty roomReposiroty;
    private final HTLClassRepository htlClassRepository;

    private void importTables() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = this.getClass().getResourceAsStream("/schooldata.json");
        try {
            JsonNode rootNode = mapper.readTree(is);
            JsonNode teacherArrayNode = rootNode.get("Schule").get("Lehrer");

            for (JsonNode teacherNode : teacherArrayNode) {
                ClassTeacher newClassTeacher = ClassTeacher
                        .builder()
                        .initials(teacherNode.get("Kürzel").asText())
                        .firstname(teacherNode.get("Vorname").asText())
                        .lastname(teacherNode.get("Familienname").asText())
                        .title(teacherNode.get("Titel").asText())
                        .build();

                HTLClass newHtlClass = HTLClass
                        .builder()
                        .name(teacherNode.get("Klasse").asText())
                        .size(teacherNode.get("Schüler").asInt())
                        .grade(Integer.parseInt(teacherNode.get("Klasse").asText().replaceAll("^([0-9]+).*", "$1")))
                        .build();

                String[] parts = teacherNode.get("Raum").asText().split("\\.");
                if(parts.length == 0)
                    throw new Exception("Malformed Data");

                int floorNumber = Integer.parseInt(parts[0]);
                Floor newFloor = switch (floorNumber) {
                    case 1 -> Floor.GROUND;
                    case 2 -> Floor.FIRST;
                    default -> null;
                };

                if(newFloor == null)
                    throw new Exception("Malformed Data");

                Room newRoom = Room
                        .builder()
                        .name(teacherNode.get("Raum").asText())
                        .floor(newFloor)
                        .build();

                newRoom.setHtlClass(newHtlClass);
                newHtlClass.setRoom(newRoom);
                newHtlClass.setClassTeacher(newClassTeacher);
                newClassTeacher.setHTLClass(newHtlClass);

                classTeacherRepository.save(newClassTeacher);
            }

        } catch (IOException e) {
            log.error("Error while reading data");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("rooms imported: " + roomReposiroty.countAll());
        log.info("classes imported: " + htlClassRepository.countAll());
        log.info("classteacher imported: " + classTeacherRepository.countAll());
    }

    private void testQueries() {
        log.info(roomReposiroty.findByClassName("9CHIF").toString());
        log.info(roomReposiroty.findByFloor(Floor.GROUND).toString());
        log.info(roomReposiroty.findAll().toString());
        log.info(roomReposiroty.countAll().toString());

        log.info(classTeacherRepository.findByGrade(4).toString());
        log.info(classTeacherRepository.findByClassname("9BHIF").toString());
        log.info(classTeacherRepository.findByName("Theresa Reischl").toString());
        log.info(classTeacherRepository.countAll().toString());

        log.info(htlClassRepository.findByName("3BHIF").toString());
        log.info(htlClassRepository.findByFloor(Floor.FIRST).toString());
        log.info(htlClassRepository.findAll().toString());
        log.info(htlClassRepository.countAll().toString());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        importTables();
        testQueries();
    }
}
