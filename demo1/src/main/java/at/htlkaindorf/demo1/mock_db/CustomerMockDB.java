package at.htlkaindorf.demo1.mock_db;

import at.htlkaindorf.demo1.pojos.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Getter
public class CustomerMockDB {
    private List<Customer> customers;

    @PostConstruct
    public void initMOckDB() {
        log.info("Initializing mock database");

        InputStream jsonInputStream = this.getClass().getResourceAsStream("/customers.json");
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
//            customers = objectMapper.readValue(jsonInputStream, new TypeReference<List<Customer>>() {});
            customers = objectMapper.readerForListOf(Customer.class).readValue(jsonInputStream);


            log.info("json mock data read successfully");
            log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customers.get(0)));
        } catch (IOException e) {
            log.error("Error reading json file");
            log.error(e.toString());
        }

    }

    public Optional<Customer> getCustomerById(Long id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<Customer> addCustomer(
            Customer customer
    ) {
        if (customers.contains(customer)) {
            return Optional.empty();
        } else {
            Long newID = customers.stream().map(Customer::getId).max(Long::compareTo).orElse(-1L) + 1;

            customer.setId(newID);
            customers.add(customer);
        }
        return Optional.of(customer);
    }

    public Optional<Customer> createOrSetCustomer(
            Long customerId,
            Customer newCustomer
    ) {
        Optional<Customer> customerOptional = getCustomerById(customerId);
        newCustomer.setId(customerId);
        if (customerOptional.isPresent()) {
            customers.set(customers.indexOf(customerOptional.get()), newCustomer);
        } else {
            customers.add(newCustomer);
        }

        return Optional.of(newCustomer);
    }

    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> customerOptional = getCustomerById(id);
        if (customerOptional.isEmpty()) {
            return Optional.empty();
        }

        Customer existing = customerOptional.get();
        existing.setId(null);

        Field[] fields = existing.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, existing);
            if(value != null) {
                ReflectionUtils.setField(field, existing, value);
            }
        }
        return Optional.of(updatedCustomer);
    }
}
