package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Library {
    private long id;
    @JsonAlias({"name"})
    @JsonProperty("name")
    private String libraryName;
    private String address;

    @JsonAlias({"departments"})
    @JsonProperty("departments")
    private List<Department> departmentList;

    public void addDepartment(Department department){
        departmentList.add(department);
    }
}
