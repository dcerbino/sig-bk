package models;

import io.ebean.Model;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Container extends Model {
    @Id
    private int id = 0;

    @ManyToOne
    private Product product;

    @NotNull
    private int footSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getFootSize() {
        return footSize;
    }

    public void setFootSize(int footSize) {
        this.footSize = footSize;
    }

    public Container(int id, Product product, int footSize) {
        this.id = id;
        this.product = product;
        this.footSize = footSize;
    }

    public Container(Product product, int footSize) {
        this.product = product;
        this.footSize = footSize;
    }


}
