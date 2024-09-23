package at.htlkaindorf;

import at.htlkaindorf.pojos.City;
import at.htlkaindorf.pojos.Order;
import at.htlkaindorf.pojos.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(new File(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "data.json").toString()));

        List<Order> orders = mapper.treeToValue(root, new TypeReference<List<Order>>() {
        });

        System.out.println("All Orders");
        orders.forEach(System.out::println);

        orders = orders.stream().filter(o -> o.getCustomer().getCity() == City.Berlin).toList();

        System.out.println("All Berlin-Orders");
        orders.forEach(System.out::println);

        orders = orders.stream().peek(o -> o.setProducts(Arrays.stream(o.getProducts()).filter(p -> p.getPrice() <= 500).toArray(Product[]::new))).toList();

        System.out.println("All Berlin-Orders without products worth more than 500€");
        orders.forEach(System.out::println);
    }
}