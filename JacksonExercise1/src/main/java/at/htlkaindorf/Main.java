package at.htlkaindorf;

import at.htlkaindorf.pojos.Employee;
import at.htlkaindorf.pojos.Industry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<Employee> listOfEmployees = mapper.readValue(new File(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data.json").toString()), new TypeReference<List<Employee>>(){});

        System.out.println("All Employees");
        listOfEmployees.forEach(System.out::println);

        listOfEmployees = listOfEmployees.stream().filter(e -> e.getCompany().getIndustry() == Industry.IT).toList();

        System.out.println("All IT-Employees");
        listOfEmployees.forEach(System.out::println);

        listOfEmployees = listOfEmployees.stream().filter(e -> e.getAge() >= 18).toList();
        System.out.println("All adult employees");
        listOfEmployees.forEach(System.out::println);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("filteredData.json"), listOfEmployees);
    }
}