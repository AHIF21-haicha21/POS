package at.htlkaindorf.eventmanagement.pojos;

import at.htlkaindorf.eventmanagement.annotations.UpdateIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
    @UpdateIgnore
    @Id
    @SequenceGenerator(sequenceName = "participant_sequence", name = "participant_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_sequence")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @UpdateIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "participants", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Event> events = new ArrayList<>();

}
