package at.htlkaindorf.pojos;

import at.htlkaindorf.adapters.YMDLocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class MaintenanceRecord {
    @XmlJavaTypeAdapter(YMDLocalDateAdapter.class)
    private LocalDate date;
    private String workPerformed;
    private String mechanic;
    @XmlIDREF
    @ToString.Exclude
    private Vehicle vehicle;
}
