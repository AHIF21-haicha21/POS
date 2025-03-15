package at.htlkaindorf.examdbservice.pojos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class Classname {
    private Long classId;
    private String classname;

    @JsonManagedReference
    private List<Student> students;
}
