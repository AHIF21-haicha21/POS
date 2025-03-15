package at.htlkaindorf.examdbservice.pojos;

import at.htlkaindorf.examdbservice.annotations.Unpatchable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class Student {
    @Unpatchable
    private Long studentId;
    private String firstname;
    private String lastname;

    @JsonManagedReference
    private List<Exam> exams;

    @JsonBackReference
    @ToString.Exclude
    private Classname classname;
}
