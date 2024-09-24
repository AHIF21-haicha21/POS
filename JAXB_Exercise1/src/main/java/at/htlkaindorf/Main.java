package at.htlkaindorf;

import at.htlkaindorf.pojos.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Fleet.class);

        Fleet fleet = (Fleet) context.createUnmarshaller().unmarshal(new File("data.xml"));

        fleet.getVehicles().forEach(System.out::println);

        Vehicle vehicle = Vehicle.builder()
                .id("V006")
                .type("Car")
                .manufacturer("Tesla")
                .year(2024)
                .mileage(10_000)
                .status("Available")
                .fuelType(FuelType.ELECTRIC)
                .owner(new Owner("Blahowsky rental", "blasva21@htl-kaindorf.at"))
                .insurance(new Insurance("Allianz", "Full", LocalDate.of(2030, 11, 12)))
                .build();
        fleet.getVehicles().add(vehicle);

        System.out.println(vehicle);

        fleet.getVehicles().getFirst().getOwner().setName("Sven Blahowsky");
        fleet.setVehicles(fleet.getVehicles().stream().peek(v -> {
            if(v.getMaintenanceRecords().stream().noneMatch(m -> m.getDate().isAfter(LocalDate.now().minusDays(30))))
                v.getAttributes().put(new QName("maintainNow"), "true");
        }).toList());

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(fleet, new File("newFleet.xml"));

    }
}