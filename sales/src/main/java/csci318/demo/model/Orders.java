package csci318.demo.model;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String customerID;


    public Orders() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "productName: "+this.productName+" quantity: "+this.quantity+" customerID: "+this.customerID;
    }

}
