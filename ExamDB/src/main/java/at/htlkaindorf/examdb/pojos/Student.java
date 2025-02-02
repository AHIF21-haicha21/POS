package at.htlkaindorf.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long studentId;

    @Column(length= 80, nullable = false)
    @NonNull
    @JsonAlias("firstname")
    private String firstName;

    @Column(length= 80, nullable = false)
    @NonNull
    @JsonAlias("lastname")
    private String lastName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "classname_id")
    @NonNull
    @JsonBackReference
    private Classname classname;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NonNull
    @Builder.Default
    @JsonManagedReference
    private List<Exam> exams = new ArrayList<>();
}
