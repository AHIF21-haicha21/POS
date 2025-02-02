package at.htlkaindorf.jpa_classinfo.pojos;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {
    @Id
    @SequenceGenerator(name = "room_sequence", sequenceName = "room_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    private Long roomId;
    private String name;

    @OneToOne(mappedBy =  "room", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ToString.Exclude
    private HTLClass htlClass;

    @Enumerated(EnumType.STRING)
    private Floor floor;
}
