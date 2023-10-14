package csci318.demo.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String productCategory;
    private String name;
    private double price;
    private double size;
    private String brand;
    private String color;

    @Column(nullable = false)
    private Integer quantity;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() { return this.quantity; }

    public void setQuantity(Integer newQuantity) {
        this.quantity = newQuantity;
    }
}

