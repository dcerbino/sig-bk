package models;

import io.ebean.Finder;
import play.data.format.Formats;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class PurchaseOrder extends BaseModel {
    public static final Finder<Long, PurchaseOrder> find = new Finder<>(PurchaseOrder.class);

    @ManyToOne
    public Product product;

    @ManyToOne
    public ProviderCompany provider;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date = new Date();

    @ManyToOne
    public Delivery delivery;

    public float quantityInTons;

}
