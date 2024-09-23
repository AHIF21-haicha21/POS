package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    @JsonAlias({"author"})
    @JsonProperty("author")
    private String bookAuthor;
    private String isbn;
    private int publicationYear;
    @JsonAlias({"availableCopies"})
    @JsonProperty("brand")
    private int copies;
}
