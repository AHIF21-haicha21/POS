package at.htlkaindorf.jacksonIntro;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // WRITE JSON

        Furniture furniture1 = new Furniture("Table", 1.6f, 0.76f, 0, Difficulty.MEDIUM);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("data.json"), furniture1);


            String jsonString = mapper.writeValueAsString(furniture1);
            System.out.println(jsonString);

            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(furniture1);
            System.out.println(jsonString);

            Furniture furniture2 = new Furniture("bed", 1.6f, 2.0f, 0.5f, Difficulty.HARD);

            Furniture[] furnitureArray = {furniture1, furniture2};

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), furnitureArray);

            List<Furniture> furnitureList = new ArrayList<>();
            furnitureList.add(furniture1);
            furnitureList.add(furniture2);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), furnitureList);

//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), furniture1);
//
//
//            // READ JSON
//
//            Furniture readFurniture = mapper.readValue(new File("data.json"), Furniture.class);
//            System.out.println(readFurniture);

            Furniture[] readFurnitureArray = mapper.readValue(new File("data.json"), Furniture[].class);

            for (Furniture fur : readFurnitureArray
            ) {
                System.out.println(fur);
            }

            List<Furniture> readFurnitureList = mapper.readerForListOf(Furniture.class).readValue(new File("data.json"));

            readFurnitureList = mapper.readValue(new File("data.json"), new TypeReference<List<Furniture>>() {
            });

            // JsonNode

            JsonNode root = mapper.readTree(new File("data.json"));
            System.out.println(root.get(0).get("productName").asText());

            for (JsonNode node : root) {
                Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
                while (iterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = iterator.next();
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}