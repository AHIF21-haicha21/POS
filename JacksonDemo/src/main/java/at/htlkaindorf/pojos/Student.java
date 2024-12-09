package at.htlkaindorf.pojos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
    @JsonBackReference
    @ToString.Exclude
    private School school;
}
