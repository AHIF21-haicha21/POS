package at.htlkaindorf.jpa_advanced.demo3;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @EmbeddedId
    private EmployeePK employeePK;

    private String name;

    @ManyToOne
    // In this case the EmployeePK contains multiple parts of another PK, here Department.
    // To link all of them together and not creating duplicate columns ('company_id' and 'company_company_id') in the DB we use the @JoinColumsn-annotation
    @JoinColumns({
            // Each @JoinColumn-annotation links one part of the EmployeePK to one part of the DepartmentPK
            // We put in the name of the part we want from the EmployeePK in 'name' and we put in the name of the referenced column
            // of the DepartmentPK in 'referencedColumnName'
            @JoinColumn(name = "companyId", referencedColumnName = "companyId", insertable = false, updatable = false),
            // To make sure that JPA doe not try to manage these two columns with the values of the EmployeePK and
            // the referenced objects PK we add 'insertable = false' and 'updatable = false' so these columns are
            // fully controlled by the EmployeePK
            // Do you like this W3ber? haha
            @JoinColumn(name = "departmentId", referencedColumnName = "departmentId", insertable = false, updatable = false)
    })
    private Department department;


}
