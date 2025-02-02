package at.htlkaindorf.jpa_classinfo.pojos;

import at.htlkaindorf.jpa_classinfo.repositories.ClassTeacherRepository;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class HTLClass {
    @Id
    @SequenceGenerator(name = "htl_class_sequence", sequenceName = "htl_class_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "htl_class_sequence")
    private Long classId;
    private String name;
    private Integer grade;
    private Integer size;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "class_teacher_id")
    @ToString.Exclude
    private ClassTeacher classTeacher;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_id")
    private Room room;
}
