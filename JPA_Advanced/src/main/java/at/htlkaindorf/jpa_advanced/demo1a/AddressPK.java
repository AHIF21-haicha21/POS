package at.htlkaindorf.jpa_advanced.demo1a;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressPK {
    private Integer zipCode;
    private String city;
    private String street;
    private Integer number;
}
