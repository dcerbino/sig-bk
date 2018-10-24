package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends Model {
    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    double weight;

    @ManyToOne
    private ProductType type;

    public Product(int id, String name, double weight, ProductType type) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
    }


    public Product(String name, double weight, ProductType type) {
        this.id = 0;
        this.name = name;
        this.weight = weight;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
