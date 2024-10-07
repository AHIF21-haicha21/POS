package at.htlkaindorf.xml;

import at.htlkaindorf.pojos.Country;
import at.htlkaindorf.pojos.DataHolder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class XML_Access {
    private static XML_Access instance;

    private XML_Access() {}

    public static XML_Access getInstance() {
        if (instance == null)
            instance = new XML_Access();
        return instance;
    }

    public DataHolder loadLocations(File f) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(DataHolder.class);
        DataHolder dataHolder = (DataHolder) context.createUnmarshaller().unmarshal(f);

        dataHolder.getCountries().forEach(country -> {
            country.getCities().forEach(city -> {
                city.setCountry(country);
                city.getCustomers().forEach(customer -> customer.setCity(city));
            });
        });

        return dataHolder;
    }

    public void saveCountry(Country country) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(DataHolder.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(country, new File(country.getCountryName() + ".xml"));

    }

}
