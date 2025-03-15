package at.htlkaindorf.booksdb.pojos;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Publisher {
    @Id
    @SequenceGenerator(name = "publisher_sequence", sequenceName = "publisher_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_sequence")
    private Integer publisherId;

    private String name;

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    /*
    * @JsonManagedReference
    * Kommt immer an die Liste bei einer "JSON-Beziehung"
    * Auf das Rückführende-Objekt, hier der publisher im Buch, kommt
    * @JsonBackReference
    * */
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();
}
