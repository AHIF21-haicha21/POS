package at.htlkaindorf.booksdb.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Integer bookId;

    private String isbn;

    @Column(scale = 38)
    private Double price;

    private String title;

    @ManyToMany(mappedBy = "books", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "publisher_id")
    @JsonBackReference
    @ToString.Exclude
    private Publisher publisher;
}
