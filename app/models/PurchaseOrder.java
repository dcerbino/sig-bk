package models;

import io.ebean.Finder;
import io.ebean.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import play.data.format.Formats;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "torder")
public class PurchaseOrder extends BaseModel {
    public static final Finder<Long, PurchaseOrder> find = new Finder<>(PurchaseOrder.class);


    @ManyToOne
    public Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    public ProviderCompany company;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date = new Date();

    @OneToMany
    @JsonIgnore
    public List<Delivery> deliveries = new ArrayList<>();

    @NotNull
    public float quantityInTons;

//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL)
//    public BillOfLoading billOfLoading;
}
