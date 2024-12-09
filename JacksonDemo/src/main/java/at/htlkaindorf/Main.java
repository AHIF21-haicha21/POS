package at.htlkaindorf;

import at.htlkaindorf.pojos.Car;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Car> cars = objectMapper.readValue(Main.class.getResource("/data.json"), new TypeReference<List<Car>>() {
        });
        List<Car> cars1;

        cars.forEach(car -> car.getHolders().forEach(holder -> holder.addCar(car)));  // kommt sicher zu plf

        for (Car car : cars
        ) {
            System.out.println(car);

        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("newCar.json"), cars);

    }
}