package at.htlkaindorf.demo1.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    @EqualsAndHashCode.Include
    private Long id;

    @JsonAlias("first_name")
    private String firstName;

    @JsonAlias({"last_name", "surname"})
    private String lastName;

    private String email;

    private Gender gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonAlias("date_of_birth")
    private LocalDate dateOfBirth;
}