package models;

import io.ebean.Finder;
import io.ebean.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseModel {
    public static final Finder<Long, Product> find = new Finder<>(Product.class);

    @NotNull
    public String name = "";

    @OneToMany
    @JsonIgnore
    List<PurchaseOrder> purchaseOrders = new ArrayList<>();

}
