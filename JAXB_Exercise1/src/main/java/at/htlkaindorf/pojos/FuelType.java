package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum FuelType {
    @XmlEnumValue("DIE")
    DIESEL,
    @XmlEnumValue("ELE")
    ELECTRIC,
    @XmlEnumValue("PET")
    PETROL
}
