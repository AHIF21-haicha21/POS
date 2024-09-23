package at.htlkaindorf.pojos;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ClassRanking {

    @XmlEnumValue("Excellent")
    A,
    @XmlEnumValue("Good")
    B,
    @XmlEnumValue("OK")
    C
}
