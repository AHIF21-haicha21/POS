package at.htlkaindorf.petshop.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Owner {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(nullable = false)
    @NonNull
    @JsonAlias("firstname")
    private String firstName;

    @Column(nullable = false)
    @NonNull
    @JsonAlias("lastname")
    private String lastName;

    public void addPet(Pet pet) {

    }

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NonNull
    @Builder.Default
    @JsonManagedReference
    private List<Pet> petList = new ArrayList<>();
}
