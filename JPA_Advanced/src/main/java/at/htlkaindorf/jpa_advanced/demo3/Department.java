package at.htlkaindorf.jpa_advanced.demo3;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @EmbeddedId
    private DepartmentPK departmentPK;

    private String departmentName;
}
