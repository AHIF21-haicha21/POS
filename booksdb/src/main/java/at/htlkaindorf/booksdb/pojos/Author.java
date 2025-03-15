package at.htlkaindorf.booksdb.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
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

    /*
    * Parameter: nullable
    * Legt fest ob der Wert in der Datenbank NULL sein darf
    * */
    @Column(length = 100, nullable = false)
    /*
     * Parameter: nullable
     * Legt fest ob der Wert im Objekt NULL sein darf
     *
     * Bspw. wenn ein Author mit einem Builder erstellt wird, wird ein Fehler geworfen
     * wenn der firstname nicht gesetzt wurde
     * */
    @NonNull
    private String firstname;

    private String lastname;

    @ManyToMany(fetch = FetchType.EAGER)
    /*
     * @JoinTable
     * Konfiguriert das Intersection-Entity zwischen Author und Book
     *
     * ManyToMany funktioniert auch ohne diese Annotation, allerdings sind Table und Columns mit
     * Standard-Namen bestückt
     *
     * @JoinTable(
     *      name="Gewünschter Tabellenname",
     *      joinColumns = @JoinColumn(name = "Gewünschter Spaltenname von diesem Entity"),
     *      inverseJoinColumns = @JoinColumn(name = "Gewünschter Spaltenname vom 'anderen' Entity")
     * )
     *
     */
    @JoinTable(
            name="bookauthor",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();
}
