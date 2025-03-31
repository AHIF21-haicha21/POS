package at.htlkaindorf.springextended.dto;

import at.htlkaindorf.springextended.pojos.Author;
import at.htlkaindorf.springextended.pojos.Publisher;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String isbn;
    private String title;
    @JsonFormat(pattern = "yyyy")
    private LocalDate releaseDate;
    private AuthorDTO author;
    private PublisherDTO publisher;
}
