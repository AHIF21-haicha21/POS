package at.htlkaindorf.springextended.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private String authorId;
    private String authorName;
    private LocalDate birthDate;

}
