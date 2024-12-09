package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {

    @XmlID
    private String countryId;

    private String countryName;

    private String countryCode;

    @XmlElementWrapper(name = "Cities")
    @XmlElement(name = "City")
    private List<City> cities;
}