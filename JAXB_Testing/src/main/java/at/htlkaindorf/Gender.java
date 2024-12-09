package at.htlkaindorf;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum()
public enum Gender {
    @XmlEnumValue("m")
    MALE,
    @XmlEnumValue("w")
    FEMALE()
}
