package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductType extends BaseModel {
    public static final Finder<Long, ProductType> find = new Finder<>(ProductType.class);

    @NotNull
    public String material = "";

    @NotNull
    public String color = "";

    @NotNull
    public String name = "";

//    @OneToMany
//    public List<Product> products = new ArrayList<>();
}
