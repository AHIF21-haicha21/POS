package at.htlkaindorf.jpademo4a.repositories;

import at.htlkaindorf.jpademo4a.pojos.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
