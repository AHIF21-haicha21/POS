package at.htlkaindorf;

import at.htlkaindorf.pojos.Book;
import at.htlkaindorf.pojos.Department;
import at.htlkaindorf.pojos.Employee;
import at.htlkaindorf.pojos.Library;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Library library = mapper.readValue(Main.class.getResource("/03_library.json"), Library.class);

        System.out.println(library);

        Department technology = new Department();
        technology.setDepartmentName("Technology");
        List<Book> books = new ArrayList<>();

        books.add(new Book("Mathefuerdummys", "Sebastian", "978-0-13-235088-4", 2008
                , 5));
        books.add(new Book("Posfuerdummys", "Thomas", "978-0-201-61622-4", 1999
                , 3));

        technology.setBookList(books);

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1001, "Clemens", "Muenzer", "Dev", LocalDate.of(2020
                , 1, 10), technology));
        employees.add(new Employee(1002, "Albin", "Albin", "Tech", LocalDate.of(2019
                , 3, 22), technology));
        employees.add(new Employee(1003, "Thomas", "Standegger", "Manager",
                LocalDate.of(2018, 5, 17), technology));

        technology.setEmployeeList(employees);
        library.addDepartment(technology);

        library.getDepartmentList().getFirst().getBookList().getFirst().setCopies(library.getDepartmentList().
                getFirst().getBookList().getFirst().getCopies() - 1);
        library.getDepartmentList().getFirst().getBookList().getLast().setCopies(library.getDepartmentList().
                getFirst().getBookList().getLast().getCopies() - 1);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("changedLibrary.json"), library);

    }
}
