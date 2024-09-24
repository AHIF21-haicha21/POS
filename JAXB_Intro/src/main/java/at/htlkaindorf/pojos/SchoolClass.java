package at.htlkaindorf.pojos;

import at.htlkaindorf.adapters.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "class")
// sorts the properties alphabetical in printing
//@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
// sets the order of properties, but should not be used due to increased errors
//@XmlType(propOrder = {"id", "name", "avgGrade", "startDate"})
@XmlAccessorType(XmlAccessType.FIELD) // ignores all getter/setter when creating the xml
@Builder
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

    @XmlAnyAttribute // All other non-mapped attributes
    private Map<QName, String> attributes = new HashMap<>();

    @XmlAnyElement // All other non-mapped attributes
    private List<Element> elements = new ArrayList<>();
}
