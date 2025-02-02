package at.htlkaindorf.petshop.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chip {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String chipId;

    @Column(length = 10, nullable = false)
    @NonNull
    private String type;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pet_id")
    @JsonBackReference
    @NonNull
    private Pet pet;
}
