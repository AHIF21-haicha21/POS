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
    /* Erstellt das Sequence-Objekt in der Datenbank
    *
    * Parameter: initialValue
    * Legt den Anfangswert der Sequence fest
    *  */
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence")
    /*
    * @GeneratedValue
    * Sort dafür, dass das Attribut automatisch generierte Werte erhält
    *
    * Parameter: strategy
    * Legt die Strategie zum Generieren der Werte fest.
    *
    * GenerationType.SEQUENCE - Erstellt ein Sequence-Objekt in der Datenbank, dass fortlaufende Nummer erzeugt
    * GenerationType.UUID - Generiert zufällige UUID-Strings
    * GenerationType.IDENTITY - Eine normale fortlaufende Nummer, wird aber von der Applikation, nicht der DB, erzeugt
    *
    * */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Integer bookId;

    private String isbn;

    @Column(scale = 38)
    private Double price;

    private String title;

    /*
    * mappedBy
    *
    * Legt die Variable in der anderen Klasse fest,
    * die an der Beziehung teilnimmt
    * */
    @ManyToMany(mappedBy = "books", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "publisher_id")
    /*
     * @JsonBackReference
     * Kommt immer an das Rückführende-Objekt bei einer "JSON-Beziehung"
     * Auf die Liste, hier books im Autor, kommt
     * @JsonManagedReference
     * */
    @JsonBackReference
    @ToString.Exclude
    private Publisher publisher;
}
