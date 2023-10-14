package csci318.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csci318.demo.controller.dto.ProductDTO;
import csci318.demo.model.Product;
import csci318.demo.model.ProductDetail;
import csci318.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductDTO dto = new ProductDTO();
                    BeanUtils.copyProperties(product, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(long id) {
        Optional<Product> contactOptional = productRepository.findById(id);

        if (contactOptional.isPresent()) {
            ProductDTO responseDTO = new ProductDTO();
            BeanUtils.copyProperties(contactOptional.get(), responseDTO);
            return responseDTO;
        } else {
            return null; // You can return null or throw a custom exception here
        }
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product savedProduct = productRepository.save(product);

        ProductDTO responseDTO = new ProductDTO();
        BeanUtils.copyProperties(savedProduct, responseDTO);

        return responseDTO;
    }

    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        Optional<Product> ProductOptional = productRepository.findById(id);

        if (ProductOptional.isPresent()) {
            Product product = ProductOptional.get();
            BeanUtils.copyProperties(productDTO, product);
            Product updatedProduct = productRepository.save(product);

            ProductDTO responseDTO = new ProductDTO();
            BeanUtils.copyProperties(updatedProduct, responseDTO);
            return responseDTO;
        } else {
            return null;
        }
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}