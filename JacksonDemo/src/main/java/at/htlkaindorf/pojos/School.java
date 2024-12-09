package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonManagedReference

    private List<Student> students;
}
