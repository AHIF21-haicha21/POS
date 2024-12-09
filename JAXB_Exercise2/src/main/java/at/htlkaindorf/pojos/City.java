package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    private long cityId;

    private String cityName;

    private String postalCode;

    @XmlIDREF
    @ToString.Exclude
    private Country country;

    @XmlElementWrapper(name="Customers")
    @XmlElement(name="Customer")
    private List<Customer> customers;
}
