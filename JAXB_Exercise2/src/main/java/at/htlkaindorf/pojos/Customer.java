package at.htlkaindorf.pojos;

import at.htlkaindorf.xml.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    private long customerId;

    private String firstname;

    private String lastname;

    private Gender gender;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfBirth;

    @ToString.Exclude
    @XmlTransient
    private City city;
}
