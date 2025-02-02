package at.htlkaindorf.jpademo4a.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Pet.findByOwnName", query = "SELECT p FROM Pet p WHERE p.name = :petName")
})
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long petId;

    @NonNull
    @Column(nullable = false, length = 50)
    private String name;

    @NonNull
    @Column(nullable = false, length = 50)
    private String petType;

    @NonNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthdate;

    @NonNull
    @Column(nullable = false)
    private Float weight;

//    @OneToOne(mappedBy = "pet")
//    private PetOwner owner;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    @ToString.Exclude
    private PetOwner owner;
}
