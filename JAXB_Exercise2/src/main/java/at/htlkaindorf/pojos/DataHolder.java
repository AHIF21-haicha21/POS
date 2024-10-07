package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="dataHolder")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataHolder {
    @XmlElementWrapper(name="Countries")
    @XmlElement(name="Country")
    private List<Country> countries;
}
