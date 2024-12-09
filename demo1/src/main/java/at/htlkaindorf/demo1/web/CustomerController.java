package at.htlkaindorf.demo1.web;

import at.htlkaindorf.demo1.mock_db.CustomerMockDB;
import at.htlkaindorf.demo1.pojos.BooleanResponse;
import at.htlkaindorf.demo1.pojos.Customer;
import at.htlkaindorf.demo1.pojos.Gender;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMockDB customerMockDB;

//    @PostConstruct
//    public void test() {
//        log.info("we have " + customerMockDB.getCustomers().size() + " customers");
//    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(
            @RequestParam(required = false, name="pageNo", defaultValue = "0") Integer pageNo
    ) {

        int pageSize = 5;
        int startIndex = pageNo * pageSize;
        int endIndex = startIndex + pageSize;

        List<Customer> customers = customerMockDB.getCustomers();
        customers.sort(Comparator.comparing(Customer::getLastName).thenComparing(Customer::getFirstName).reversed());

        return ResponseEntity.ok(customers.subList(startIndex, endIndex));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId)
    {
        log.info("GET: getCustomer");
        Optional<Customer> customer = customerMockDB.getCustomerById(customerId);
//
//        if(customer.isPresent())
//            return ResponseEntity.ok(customer.get());
//        return ResponseEntity.notFound().build();

        return ResponseEntity.of(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(
            @RequestBody Customer customer
    ){
        log.info("POST: addCustomer");
        log.info("customer: {}", customer.toString());

        Optional<Customer> customerOptional = customerMockDB.addCustomer(customer);
        if(customerOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();

            return ResponseEntity.created(location).body(customerOptional.get());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> createOrSetCustomer(
            @PathVariable("id") Long id,
            @RequestBody Customer customer
    ) {
        log.info("PUT: updateCustomer");

        Optional<Customer> customerOptional = customerMockDB.createOrSetCustomer(id, customer);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(customerOptional.orElse(null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer
    ) {
        Optional<Customer> customerOptional = customerMockDB.updateCustomer(id, customer);
        if(customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerOptional.orElse(null));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Customer>> filterCustomer(
            @RequestParam(name = "gender", required = true) Gender gender,
            @RequestParam(name = "year", required = true) int year
    ) {
        log.info("GET: filterCustomer");
        List<Customer> customers = customerMockDB.getCustomers();
        customers = customers.stream().filter(customer -> customer.getGender() == gender && customer.getDateOfBirth().getYear() == year).toList();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<BooleanResponse> getCustomerById(@PathVariable("id") Long id, @RequestParam(name="month", required = true) int month)
    {
        log.info("GET: getCustomerById");
        Optional<Customer> customerOptional = customerMockDB.getCustomerById(id);
        if(customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BooleanResponse(customerOptional.get().getDateOfBirth().getMonthValue() == month));
        }
        return ResponseEntity.notFound().build();
    }
}
