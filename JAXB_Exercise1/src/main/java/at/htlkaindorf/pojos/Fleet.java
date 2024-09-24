package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Fleet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fleet {
    @XmlElement(name = "Vehicle")
    private List<Vehicle> vehicles;
}
