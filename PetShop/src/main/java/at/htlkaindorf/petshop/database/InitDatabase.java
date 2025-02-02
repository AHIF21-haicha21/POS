package at.htlkaindorf.petshop.database;

import at.htlkaindorf.petshop.pojos.Chip;
import at.htlkaindorf.petshop.pojos.Owner;
import at.htlkaindorf.petshop.repository.ChipRepository;
import at.htlkaindorf.petshop.repository.OwnerRepository;
import at.htlkaindorf.petshop.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDatabase {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final ChipRepository chipRepository;

    @PostConstruct
    private void initData() {
        final String dataSourceFileName = "data.json";
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            List<Owner> ownerList = mapper.readerForListOf(Owner.class).readValue(this.getClass().getResourceAsStream("/" + dataSourceFileName));

            ownerRepository.saveAll(ownerList);

            log.info(String.format("Pets imported: %d", petRepository.count()));
            log.info(String.format("Chips imported: %d", chipRepository.count()));
            log.info(String.format("Owner imported: %d", ownerRepository.count()));
        } catch (IOException e) {
            log.error(String.format("Error while reading %s", dataSourceFileName));
            throw new RuntimeException(e);
        }
    }
}
