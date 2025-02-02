package at.htlkaindorf.jpademo4a.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Address {

    @Id
    @SequenceGenerator(name = "address_sequence", initialValue = 10, sequenceName = "address_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long addressId;

    @Column(name = "city", length = 50)
    private String cityName;

    @Column(length = 100)
    private String streetName;

    @ManyToMany
    @JoinTable(
            name = "owner_address",
            joinColumns =  {@JoinColumn(name = "address_id")},
            inverseJoinColumns =  {@JoinColumn(name = "owner_id")}
    )
    private List<PetOwner> petOwnersList = new ArrayList<>();

    public void addPetOwner(PetOwner owner1) {
        petOwnersList.add(owner1);
    }
}
