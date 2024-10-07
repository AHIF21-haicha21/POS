package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Gender {
    @XmlEnumValue("Male")
    MALE,
    @XmlEnumValue("Female")
    FEMALE
}
