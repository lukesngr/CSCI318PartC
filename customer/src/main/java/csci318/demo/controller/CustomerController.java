package csci318.demo.controller;

import csci318.demo.model.Customer;
import csci318.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    @PostMapping("/")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a specific customer by ID
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    @GetMapping("/checkPassword/{id}")
    public boolean checkIfPasswordIsCorrect(@PathVariable Long id, @RequestBody String password) {
        return customerRepository.findById(id).get().getPassword() == password;
    }

    // Update a customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        updatedCustomer.setId(id);
        return customerRepository.save(updatedCustomer);
    }

    @KafkaListener(topics = "sales", groupId = "salesConsumer")
    public void listen(String message) {
        Pattern pattern = Pattern.compile("customerID: (\\d+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String customerIdStr = matcher.group(1);
            System.out.println(customerIdStr);
            Long customerId = Long.parseLong(customerIdStr);
            Customer customerToUpdate = customerRepository.findById(customerId).get();
            customerToUpdate.setMoney(customerToUpdate.getMoney()-1);
            customerRepository.save(customerToUpdate);
        } else {
            System.out.println(message);
        }
    }

    // Delete a customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
