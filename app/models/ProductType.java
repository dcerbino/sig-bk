package models;

import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductType extends BaseModel {
    @NotNull
    public String material = "";

    @NotNull
    public String Color = "";

    @NotNull
    public String name = "";

    @OneToMany
    public List<Product> products = new ArrayList<>();
}
