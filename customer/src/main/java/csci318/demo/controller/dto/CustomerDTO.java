package csci318.demo.controller.dto;

public class CustomerDTO  {

    private Long id;

    private String companyName;

    private String address;

    private String country;

    private String password;

    private Integer money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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