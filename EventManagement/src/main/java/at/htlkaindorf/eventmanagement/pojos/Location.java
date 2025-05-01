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
public class Location {

    @UpdateIgnore
    @Id
    @SequenceGenerator(sequenceName = "location_sequence", name = "location_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_sequence")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer capacity;

    @UpdateIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Event> events = new ArrayList<>();
}
