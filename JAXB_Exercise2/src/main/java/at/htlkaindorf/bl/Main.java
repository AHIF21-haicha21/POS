package at.htlkaindorf.bl;

import at.htlkaindorf.pojos.Customer;
import at.htlkaindorf.pojos.DataHolder;
import at.htlkaindorf.xml.XML_Access;
import jakarta.xml.bind.JAXBException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private DataHolder dataHolder;

    private boolean isDataLoaded() {
        if (dataHolder == null) {
            System.out.println("No data loaded!");
            System.out.println("press ENTER to continue");
            System.console().readLine();
            return false;
        }
        return true;
    }

    private void printMenu() {
        System.out.println("XML Access");
        System.out.println("1.  Load Locations");
        System.out.println("2.  Save Country");
        System.out.println("3.  Search all cities of one country");
        System.out.println("4.  Search all customers of a defined country");
        System.out.println("5.  Search all customers of a defined city");
        System.out.println("6.  Filter customers by country and/or city");
        System.out.println("7.  Find oldest and youngest customer");
        System.out.println("8.  Find all customers whose birthday is today");
        System.out.println("9.  Search the amount of all customers of a country, grouped by cities");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            printMenu();

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice");
                continue;
            }

            switch (choice) {
                case 1:
                    loadData();
                    break;
                case 2:
                    saveCountry();
                    break;
                case 3:
                    searchCitiesByCountry();
                    break;
                case 4:
                    searchCustomersByCountry();
                    break;
                case 5:
                    searchCustomersByCity();
                    break;
                case 6:
                    filterByCountryOrCity();
                    break;
                case 7:
                    findOldestYoungest();
                    break;
                case 8:
                    findBirthday();
                    break;
                case 9:
                    searchAmount();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (true);
    }

    private void loadData() {
        try {
            System.out.print("Enter the path to the file: ");
            String path = System.console().readLine();
            File f = new File(path);

            if (!f.exists()) {
                System.out.println("File not found");
                return;
            }

            dataHolder = XML_Access.getInstance().loadLocations(f);
            System.out.println("Data loaded successfully!");

        } catch (JAXBException e) {
            System.out.println("Error loading data" + e);
        }

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void saveCountry() {
        if (!isDataLoaded()) return;

        System.out.print("Enter the country name: ");
        String countryName = System.console().readLine();

        dataHolder.getCountries().stream()
                .filter(country -> country.getCountryName().equals(countryName))
                .findFirst()
                .ifPresentOrElse(country -> {
                    try {
                        XML_Access.getInstance().saveCountry(country);
                        System.out.println("Country saved successfully!");
                    } catch (JAXBException e) {
                        System.out.println("Error saving country");
                    }
                }, () -> {
                    System.out.println("Country not found!");
                });

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void searchCitiesByCountry() {
        if (!isDataLoaded()) return;

        System.out.print("Enter the country name: ");
        String countryName = System.console().readLine();

        dataHolder.getCountries().stream()
                .filter(country -> country.getCountryName().equals(countryName))
                .findFirst()
                .ifPresentOrElse(country -> {
                    country.getCities().forEach(System.out::println);
                }, () -> {
                    System.out.println("Country not found!");
                });

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void searchCustomersByCountry() {
        if (!isDataLoaded()) return;

        System.out.print("Enter the country name: ");
        String countryName = System.console().readLine();

        dataHolder.getCountries().stream().filter(country -> country.getCountryName().equals(countryName))
                .findFirst()
                .ifPresentOrElse(country -> {
                    country.getCities().forEach(city -> {
                        city.getCustomers().forEach(System.out::println);
                    });
                }, () -> {
                    System.out.println("Country not found!");
                });

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void searchCustomersByCity() {
        if (!isDataLoaded()) return;

        System.out.print("Enter the city name: ");
        String cityName = System.console().readLine();

        dataHolder.getCountries()
                .forEach(country ->
                        country.getCities().stream()
                                .filter(city -> city.getCityName().equals(cityName))
                                .findFirst()
                                .ifPresent(city -> {
                                    city.getCustomers().forEach(System.out::println);
                                })
                );

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void findOldestYoungest() {
        if (dataHolder == null) {
            System.out.println("No data loaded!");
            System.out.println("press ENTER to continue");
            System.console().readLine();
            return;
        }


        Customer oldest = dataHolder.getCountries().stream()
                .flatMap(country -> country.getCities().stream())
                .flatMap(city -> city.getCustomers().stream())
                .min(Comparator.comparing(Customer::getDateOfBirth))
                .orElse(null);
        Customer youngest = dataHolder.getCountries().stream()
                .flatMap(country -> country.getCities().stream())
                .flatMap(city -> city.getCustomers().stream())
                .max(Comparator.comparing(Customer::getDateOfBirth))
                .orElse(null);

        System.out.println("Oldest: " + oldest);
        System.out.println("Youngest: " + youngest);

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void findBirthday() {
        if (!isDataLoaded()) return;

        LocalDateTime now = LocalDateTime.now();
        List<Customer> customers = dataHolder.getCountries().stream()
                .flatMap(country -> country.getCities().stream())
                .flatMap(city -> city.getCustomers().stream())
                .filter(customer ->
                        customer.getDateOfBirth().getDayOfMonth() == now.getDayOfMonth() &&
                                customer.getDateOfBirth().getMonth() == now.getMonth()
                )
                .toList();

        if (customers.isEmpty()) {
            System.out.println("No customers have birthday today");
        } else {
            customers.forEach(System.out::println);
        }

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void searchAmount() {
        if (!isDataLoaded()) return;

        System.out.print("Enter the country name: ");
        String countryName = System.console().readLine();

        dataHolder.getCountries().stream()
                .filter(country -> country.getCountryName().equals(countryName))
                .findFirst()
                .ifPresentOrElse(country -> {
                    country.getCities().forEach(city ->
                            System.out.println(city.getCityName() + ": " + (long) city.getCustomers().size())
                    );
                }, () -> {
                    System.out.println("Country not found");
                });

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    private void filterByCountryOrCity() {
        if (!isDataLoaded()) return;

        System.out.print("Enter the country name: ");
        String countryName = System.console().readLine();
        System.out.print("Enter the city name: ");
        String cityName = System.console().readLine();

        dataHolder.getCountries().stream()
                .filter(country -> countryName.isEmpty() || country.getCountryName().equals(countryName))
                .flatMap(country -> country.getCities().stream())
                .filter(city -> cityName.isEmpty() || city.getCityName().equals(cityName))
                .flatMap(city -> city.getCustomers().stream())
                .forEach(System.out::println);

        System.out.println("press ENTER to continue");
        System.console().readLine();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}
