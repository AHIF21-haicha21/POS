package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long id;
    @JsonAlias({"first_name"})
    @JsonProperty("first_name")
    private String firstName;
    @JsonAlias({"last_name"})
    @JsonProperty("last_name")
    private String lastName;
    @JsonAlias({"role"})
    @JsonProperty("role")
    private String job;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonAlias({"hireDate"})
    @JsonProperty("hireDate")
    private LocalDate date;
    @JsonBackReference
    @ToString.Exclude
    private Department department;
}
