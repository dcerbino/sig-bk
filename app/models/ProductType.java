package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Entity
public class ProductType extends Model {
    @Id
    private int id=0;

    @NotNull
    private String material="";

    @NotNull
    private String Color="";

    @NotNull
    private String name="";

    @OneToMany
    private List<Product> products;

    public ProductType(String material, String color, String name) {
        this(0,material,color,name);
    }

    public ProductType(int id, String material, String color, String name) {
        this(id, material, color, name, new ArrayList<>());
    }

    public ProductType(int id, String material, String color, String name, List<Product> products) {
        this.id = id;
        this.material = material;
        Color = color;
        this.name = name;
        this.products= products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
