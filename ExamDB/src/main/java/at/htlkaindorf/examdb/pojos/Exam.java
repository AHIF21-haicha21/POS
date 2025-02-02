package at.htlkaindorf.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {
    @Id
    @NonNull
    private Long examId;

    @Column(nullable = false)
    @NonNull
    private Integer duration;

    @Column(nullable = false)
    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonAlias("dateofexam")
    private LocalDate dateOfExam;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    @NonNull
    @JsonBackReference
    private Student student;
}
