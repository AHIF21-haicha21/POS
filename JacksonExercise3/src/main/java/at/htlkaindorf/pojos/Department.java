package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @JsonAlias({"name"})
    @JsonProperty("name")
    private String departmentName;
    @JsonAlias({"books"})
    @JsonProperty("books")
    private List<Book> bookList;

    @JsonManagedReference
    @JsonAlias({"staff"})
    @JsonProperty("staff")
    private List<Employee> employeeList;
}
