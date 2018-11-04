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
public class Order extends BaseModel {
    public static final Finder<Long, Order> find = new Finder<>(Order.class);


    @ManyToOne(cascade = CascadeType.ALL)
    public Product product;

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date = new Date();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL)
//    public BillOfLoading billOfLoading;
}
