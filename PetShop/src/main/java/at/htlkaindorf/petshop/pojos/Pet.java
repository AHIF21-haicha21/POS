package at.htlkaindorf.petshop.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pet {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer petId;

    @Column(nullable = false)
    @NonNull
    private String name;

    private String picture;

    @Column(nullable = false)
    @NonNull
    private String type;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @NonNull
    private Gender gender;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "chip_id", nullable = false)
    @JsonManagedReference
    @NonNull
    private Chip chip;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    @NonNull
    private Owner owner;
}
