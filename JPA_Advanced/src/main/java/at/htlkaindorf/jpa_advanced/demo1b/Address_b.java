package at.htlkaindorf.jpa_advanced.demo1b;


import jakarta.persistence.*;


@Entity
public class Address_b {
    // Embed the primary key inside of an object of the PK-Class
    @EmbeddedId
    private AddressPK_b addressPK;

    private String name;
}
