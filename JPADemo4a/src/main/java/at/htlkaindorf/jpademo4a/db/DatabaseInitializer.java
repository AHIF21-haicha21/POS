package at.htlkaindorf.jpademo4a.db;

import at.htlkaindorf.jpademo4a.pojos.Address;
import at.htlkaindorf.jpademo4a.pojos.Pet;
import at.htlkaindorf.jpademo4a.pojos.PetOwner;
import at.htlkaindorf.jpademo4a.repositories.AddressRepository;
import at.htlkaindorf.jpademo4a.repositories.PetOwnerRepository;
import at.htlkaindorf.jpademo4a.repositories.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private final AddressRepository addressRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final PetRepository petRepository;

    @PostConstruct
    public void initAddress() {

        Address address = Address.builder()
                .cityName("Graz")
                .streetName("Am Ring 3")
                .build();

        addressRepository.save(address);
        addressRepository.save(Address.builder().cityName("Leibnitz").streetName("Grazer Straße 202").build());
    }

    @PostConstruct
    public void initPetOwner() {
        PetOwner owner1 = PetOwner.builder().name("Johannes").addressList(new ArrayList<>()).build();

        Address address1 = Address.builder().cityName("Graz").streetName("Am Ring 2").petOwnersList(new ArrayList<>()).build();

//        address1 = addressRepository.save(address1);

        owner1.addAddress(address1);


        address1.addPetOwner(owner1);

//        addressRepository.save(address1);

        owner1 = petOwnerRepository.save(owner1);
    }

    @PostConstruct
    public void initPets() throws JsonProcessingException {

        PetOwner petOwner = new PetOwner("John");

        for (int i = 0; i < 40; i++) {
            Pet pet = new Pet("Bello - " + i, "dog", LocalDate.now(), 5f);
            petOwner.addPet(pet);
            petOwnerRepository.save(petOwner);
        }

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(petOwner);

        log.info(value);

        for (Pet pet : petRepository.findAllByPetType("cat")) {
            log.info(pet.toString());
        }

        for (Pet pet : petRepository.findPetsByOwnerName("John")) {
            log.info(pet.toString());
        }
//
//        log.info(petRepository.findByOwnName("Bello").toString());
//
//        Sort sorting = Sort.by("birthdate").descending().and(Sort.by("name"));
//
//        List<Pet> list1 = petRepository.findAll(sorting);
//
//        for (Pet pet : list1) {
//            log.info(pet.toString());
//        }
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
//        Pageable page = PageRequest.of(1, 10, sorting);
//        List<Pet> petsByType = petRepository.findAllByPetType("cat",page);
    }
}
