package at.htlkaindorf.petshop.repository;

import at.htlkaindorf.petshop.pojos.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
