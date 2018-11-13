package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PurchaseOrder extends BaseModel {
    public static final Finder<Long, PurchaseOrder> find = new Finder<>(PurchaseOrder.class);

    @ManyToOne
    public Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    public ProviderCompany provider;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date = new Date();

    @OneToOne
    public Delivery delivery;

    public Float quantityInTons;

}
