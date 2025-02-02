package at.htlkaindorf.petshop.web;

import at.htlkaindorf.petshop.pojos.Pet;
import at.htlkaindorf.petshop.repository.ChipRepository;
import at.htlkaindorf.petshop.repository.OwnerRepository;
import at.htlkaindorf.petshop.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/petshop/pets")
public class PetsController {
    private final PetRepository petRepository;

    @GetMapping("/all")
    private ResponseEntity<List<Pet>> getAllPets(
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "petType", required = false) String petType
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if(petType != null)
            return ResponseEntity.ok(petRepository.findAllByType(petType, page).stream().toList());
        return ResponseEntity.ok(petRepository.findAll(page).stream().toList());
    }

    @GetMapping("/types")
    private ResponseEntity<List<String >> getAllPetTypes() {
        return ResponseEntity.ok(petRepository.findAllPetTypes());
    }


}
