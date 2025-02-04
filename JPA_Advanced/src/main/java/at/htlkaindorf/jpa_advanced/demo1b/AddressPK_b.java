package at.htlkaindorf.jpa_advanced.demo1b;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// If Lombok is not used you need ot overwrite the hashcode() and equals() functions
@Data
@AllArgsConstructor
@NoArgsConstructor
// Mark the PK-Class as Embeddable to be able to use it inside the @EmbeddedId-annotation
@Embeddable
// Mark the PK-Class as Serializable
public class AddressPK_b implements Serializable {
    private Integer zipCode;
    private String city;
    private String street;
    private Integer number;
}
