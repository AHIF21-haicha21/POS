package at.htlkaindorf.pojos;

import at.htlkaindorf.adapters.YDMLocalDateAdapter;
import at.htlkaindorf.adapters.YMDLocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Insurance {
    private String company;
    private String coverage;
    @XmlJavaTypeAdapter(YDMLocalDateAdapter.class)
    @XmlElement(name = "expDate")
    private LocalDate expiration;
}
