package at.htlkaindorf;

import at.htlkaindorf.pojos.ClassRanking;
import at.htlkaindorf.pojos.SchoolClass;
import at.htlkaindorf.pojos.Subject;
import at.htlkaindorf.pojos.Teacher;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLTester {

    // Write the XML file
    public void marshall() throws JAXBException {
        SchoolClass schoolClass = new SchoolClass(1256, "4AHIF", 1.4f, LocalDate.of(2024, 9, 9), ClassRanking.A, List.of(1, 2, 3, 4), new Teacher());

        schoolClass.getClassTeacher().setFirstName("Theresa");
        schoolClass.getClassTeacher().setLastName("Reischl");
        schoolClass.getClassTeacher().setSchoolClass(schoolClass);
        schoolClass.getClassTeacher().setSubjects(new ArrayList<>());
        schoolClass.getClassTeacher().getSubjects().add(new Subject("BESP", 1.0f, schoolClass.getClassTeacher()));
        schoolClass.getClassTeacher().getSubjects().add(new Subject("GGP", 5.0f, schoolClass.getClassTeacher()));

        JAXBContext jaxbContext = JAXBContext.newInstance(SchoolClass.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(schoolClass, new File("schoolClass.xml"));
    }

    // Read the XML file
    public void unmarshall() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SchoolClass.class);
        SchoolClass schoolClass = (SchoolClass) context.createUnmarshaller().unmarshal(new File("schoolClass.xml"));

        System.out.println(schoolClass);
    }

    public static void main(String[] args) throws JAXBException {
        XMLTester xmlTester = new XMLTester();

        xmlTester.marshall();
        xmlTester.unmarshall();
    }
}
