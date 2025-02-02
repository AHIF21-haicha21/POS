package at.htlkaindorf.jpa_classinfo.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ClassTeacher {
    @Id
    @SequenceGenerator(name = "class_teacher_sequence", sequenceName = "class_teacher_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_teacher_sequence")
    private Long teacherId;
    private String initials;
    private String firstname;
    private String lastname;
    private String title;

    @OneToOne(mappedBy= "classTeacher", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private HTLClass hTLClass;
}
