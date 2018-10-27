package models;

import io.ebean.Model;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends Model {
    @Id
    private int id=0;

    @NotNull
    private String name;

    @NotNull
    double weight;

    @ManyToOne
    private ProductType type;

    @OneToMany
    private List<Container> containers = new ArrayList<>();

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

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
