package at.htlkaindorf.eventmanagement.database;

import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Location;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DatabaseInitializer {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAutoMode;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        if (!ddlAutoMode.contains("create"))
            return;

        ClassPathResource script = new ClassPathResource("insert_mock_data.sql");

        if (!script.exists()) {
            log.warn("No script fot data import was found!");
            return;
        }

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(script);
        populator.execute(dataSource);
    }
}
