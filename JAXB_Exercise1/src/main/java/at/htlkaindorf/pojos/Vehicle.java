package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
public class Vehicle {
    @XmlAttribute
    @XmlID
    private String id;

    private String type;

    private String manufacturer;

    private int year;

    @XmlElement(name = "km")
    private int mileage;

    private String status;

    @XmlElement(name = "FuelType")
    private FuelType fuelType;

    @XmlElement(name = "Driver")
    @Builder.Default
    private List<Driver> drivers = new ArrayList<>();

    @XmlElementWrapper(name = "Maintenance")
    @XmlElement(name = "Record")
    @Builder.Default
    private List<MaintenanceRecord> maintenanceRecords = new ArrayList<>();

    @XmlElement(name = "Insurance")
    private Insurance insurance;

    @XmlElement(name = "Owner")
    private Owner owner;

    @XmlAnyAttribute
    @Builder.Default
    private Map<QName, String> attributes = new HashMap<>();
}
