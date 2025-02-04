package at.htlkaindorf.jpa_advanced.demo2;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Book {

    @EmbeddedId
    private BookPK bookPK;

    private String title;

    @ManyToOne
    // Due to the Many-to-One relation author would be another authoer_id in the database
    // to make sure that the FK for the author property and the author-PK are the same column in the DB
    // we use @MapsId to link the property to the PK-part

    // keep in mind that the instance variable has to be named exactly the same in
    // the referenced class, the PK of this class and the @MapsId-annotation.
    // In this case: In Author, BookPK and below
    @MapsId("authorId")
    // Snake-Case conversion only happens for instance variables
    // To properly rename the identifying-FK write the name in Snake-Case directly
    @JoinColumn(name = "author_id")
    private Author author;
}
