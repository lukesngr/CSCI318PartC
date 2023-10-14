package csci318.demo.controller;

import csci318.demo.model.Product;
import csci318.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Get all products
    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a specific product by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    // Update a product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    @KafkaListener(topics = "sales", groupId = "salesConsumer")
    public void listen(String message) {
        Pattern pattern = Pattern.compile("productName: (\\S+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String productName = matcher.group(1);
            System.out.println(productName);
            Product productToUpdate = productRepository.findAll().stream()
                    .filter(product -> productName.equals(product.getName()))
                    .findAny()
                    .orElse(null);;
            productToUpdate.setQuantity(productToUpdate.getQuantity()-1);
            productRepository.save(productToUpdate);
        } else {
            System.out.println(message);
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}