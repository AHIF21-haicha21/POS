package at.htlkaindorf.springextended.pojos;

import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();
}
