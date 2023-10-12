package csci318.demo.service;

import csci318.demo.controller.dto.CustomerDTO;
import csci318.demo.model.Customer;
import csci318.demo.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO responseDTO = new CustomerDTO();
        BeanUtils.copyProperties(savedCustomer, responseDTO);

        return responseDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> {
                    CustomerDTO dto = new CustomerDTO();
                    BeanUtils.copyProperties(customer, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            CustomerDTO responseDTO = new CustomerDTO();
            BeanUtils.copyProperties(customerOptional.get(), responseDTO);
            return responseDTO;
        } else {
            return null; // You can return null or throw a custom exception here
        }
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            BeanUtils.copyProperties(customerDTO, customer);
            Customer updatedCustomer = customerRepository.save(customer);

            CustomerDTO responseDTO = new CustomerDTO();
            BeanUtils.copyProperties(updatedCustomer, responseDTO);
            return responseDTO;
        } else {
            return null; // You can return null or throw a custom exception here
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
