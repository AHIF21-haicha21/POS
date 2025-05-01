package at.htlkaindorf.eventmanagement.pojos;

import at.htlkaindorf.eventmanagement.annotations.UpdateIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organizer {
    @UpdateIgnore
    @Id
    @SequenceGenerator(sequenceName = "organizer_sequence", name = "organizer_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizer_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private Long phone;
}
