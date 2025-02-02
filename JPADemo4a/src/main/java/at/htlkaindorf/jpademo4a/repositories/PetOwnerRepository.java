package at.htlkaindorf.jpademo4a.repositories;

import at.htlkaindorf.jpademo4a.pojos.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, String> {

}
