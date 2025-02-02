package at.htlkaindorf.jpademo4a.repositories;

import at.htlkaindorf.jpademo4a.pojos.Pet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    // Derived Queries
    public List<Pet> findAllByPetType(String petType, Pageable page);
    public List<Pet> findAllByPetType(String petType);

    // Query Method
    @Query("SELECT p FROM Pet p WHERE p.owner.name = ?1 ORDER BY p.petType")
    public List<Pet> findPetsByOwnerName(String ownerName);

    // Named Query
    public Pet findByOwnName(String petName);
}
