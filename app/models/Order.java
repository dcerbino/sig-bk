package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "torder")
public class Order extends BaseModelWithStringId {
    public static final Finder<String, Order> find = new Finder<>(Order.class);


    @ManyToOne(cascade = CascadeType.ALL)
    public List<Product> products = new ArrayList<>();

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date = new Date();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public BillOfLoading billOfLoading;
}
