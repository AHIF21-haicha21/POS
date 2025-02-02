package at.htlkaindorf.petshop.repository;

import at.htlkaindorf.petshop.pojos.Chip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChipRepository extends JpaRepository<Chip, String > {

    @Query("SELECT DISTINCT c.type FROM Chip c ORDER BY c.type")
    public List<String> findAllChipTypes();
}
