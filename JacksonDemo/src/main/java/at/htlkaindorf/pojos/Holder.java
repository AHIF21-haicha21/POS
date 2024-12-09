package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Holder {
    private String name;
    private int age;
    private String city;

    @ToString.Exclude
    @JsonIgnore
    List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }
}
