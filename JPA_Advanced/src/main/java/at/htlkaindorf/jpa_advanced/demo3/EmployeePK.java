package at.htlkaindorf.jpa_advanced.demo3;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeePK implements Serializable {
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
}
