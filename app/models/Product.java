package models;

import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseModel {

    @NotNull
    public String name;

    @NotNull
    public Double weight;

    @ManyToOne
    public ProductType type;

    @OneToMany
    public List<Container> containers = new ArrayList<>();

//    @ManyToMany
//    public List<Order> orders = new ArrayList<>();
}
