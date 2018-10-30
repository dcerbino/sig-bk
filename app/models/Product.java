package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseModel {
    public static final Finder<Long, Product> find = new Finder<>(Product.class);


    @NotNull
    public String name = "";

    @ManyToOne
    public ProductType type;

    @OneToMany
    public List<Container> containers = new ArrayList<>();

}
