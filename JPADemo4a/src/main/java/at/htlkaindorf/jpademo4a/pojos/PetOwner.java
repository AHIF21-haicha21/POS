package at.htlkaindorf.jpademo4a.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Builder
public class PetOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ownerId;
    @Column(length = 100, nullable = false) // Exception wenn Null in die DB kommen will
    @NonNull // Exception bei object creation mit Null
    private String name;


    @ManyToMany(mappedBy = "petOwnersList", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Address> addressList = new ArrayList<>();
    
    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();

    public void addAddress(Address address) {
        addressList.add(address);
    }

    public void addPet(Pet pet) {
        pet.setOwner(this);
        pets.add(pet);
    }
}
