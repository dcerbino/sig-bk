package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;

import javax.persistence.CascadeType;
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

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    public ProductType productType;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Container> containers = new ArrayList<>();

}
