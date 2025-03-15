package at.htlkaindorf.examdbservice.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class Exam {
    private Long examId;
    private Integer duration;

    @JsonAlias("dateofexam")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfExam;

    @JsonBackReference
    @ToString.Exclude
    private Student student;
}
