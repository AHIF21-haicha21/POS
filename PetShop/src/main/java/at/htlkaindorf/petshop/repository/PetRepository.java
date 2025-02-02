package at.htlkaindorf.petshop.repository;

import at.htlkaindorf.petshop.pojos.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Integer> {

    public Page<Pet> findAllByType(String petType, Pageable page);

    @Query("SELECT DISTINCT p.type FROM Pet p ORDER BY p.type")
    public List<String> findAllPetTypes();
}
