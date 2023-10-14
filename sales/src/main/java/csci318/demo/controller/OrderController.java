package csci318.demo.controller;

import csci318.demo.model.Orders;
import csci318.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import csci318.demo.kafka.KafkaProducerConfig;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Create a new order
    @PostMapping("/")
    public String createOrder(@RequestBody Orders order) {
        final String productsURL = "http://127.0.0.1:8081/product/name/" + order.getProductName();
        System.out.println(order.getProductName());

        RestTemplate restTemplate = new RestTemplate();
        Boolean productExists = restTemplate.getForObject(productsURL, Boolean.class);
        KafkaProducerConfig producerConfig = new KafkaProducerConfig();

        if(productExists) {
            KafkaTemplate<String, String> kafkaTemplate = producerConfig.kafkaTemplate();
            kafkaTemplate.send("sales", order.toString());
            orderRepository.save(order);
            return "Order placed successfully";
        }else{
            System.out.println(productExists);
            return "Product does not exist";
        }

    }

    // Get all orders
    @GetMapping("/")
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get a specific order by ID
    @GetMapping("/{id}")
    public Optional<Orders> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id);
    }


    // Delete a order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
