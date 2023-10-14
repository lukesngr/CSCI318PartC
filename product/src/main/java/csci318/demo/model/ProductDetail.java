package csci318.demo.model;

import javax.persistence.Entity;

@Entity
public class ProductDetail extends Product {

    private String description;
    private String comment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
