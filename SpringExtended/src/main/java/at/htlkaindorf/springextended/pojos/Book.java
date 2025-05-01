package at.htlkaindorf.springextended.pojos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
})
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Integer id;

    private String isbn;

    private Double price;

    @Column(nullable = false)
    private String title;

    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publisher publisher;
}
