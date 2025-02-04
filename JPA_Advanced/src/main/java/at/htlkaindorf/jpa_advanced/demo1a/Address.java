package at.htlkaindorf.jpa_advanced.demo1a;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;


@Entity
// Define the class the composite key inherits its parts from
@IdClass(AddressPK.class)
public class Address {
    // mark all parts of the PK with @Id
    // All instance variables need to be named exactly the same as in the PK-Class
    @Id
    private Integer zipCode;
    @Id
    private String city;
    @Id
    private String street;
    @Id
    private Integer number;

    private String name;
}
