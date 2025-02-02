package at.htlkaindorf.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classname {
    @Id
    @NonNull
    private Long classId;

    @Column(length = 10, nullable = false)
    @NonNull
    @JsonAlias("classname")
    private String className;

    @OneToMany(mappedBy = "classname", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NonNull
    @Builder.Default
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();
}
