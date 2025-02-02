package at.htlkaindorf.booksdb.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    private Integer authorId;

    @Column(length = 100)
    private String firstname;

    private String lastname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="bookauthor",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();
}
