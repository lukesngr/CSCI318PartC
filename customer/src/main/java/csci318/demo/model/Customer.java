package csci318.demo.model;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String password;

    private Integer money;

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() { return this.password; }

    public void setPassword(String newPassword) { this.password = newPassword; }

    public Integer getMoney() { return this.money; }

    public void setMoney(Integer newMoney) { this.money = newMoney; }
}
