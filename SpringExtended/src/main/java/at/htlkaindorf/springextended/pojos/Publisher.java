package at.htlkaindorf.springextended.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Publisher {
    @Id
    @SequenceGenerator(name = "publisher_sequence", sequenceName = "publisher_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_sequence")
    private Long id;

    private String name;
}
