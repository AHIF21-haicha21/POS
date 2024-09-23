package at.htlkaindorf.pojos;

import at.htlkaindorf.adapters.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "class")
// sorts the properties alphabetical in printing
//@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
// sets the order of properties, but should not be used due to increased errors
//@XmlType(propOrder = {"id", "name", "avgGrade", "startDate"})
@XmlAccessorType(XmlAccessType.FIELD) // ignores all getter/setter when creating the xml
public class SchoolClass
{
    @XmlAttribute(name = "classId")
    private long id;

    @XmlElement(name = "className")
    @XmlID
    private String name;

    @XmlTransient
    private float avgGrade;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    private ClassRanking ranking;

    @XmlElementWrapper(name = "grades")
    @XmlElement(name = "grade")
    private List<Integer> grades;


    private Teacher classTeacher;
}
