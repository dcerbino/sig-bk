package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class BillOfLoading extends BaseModel {

    public static final Finder<Long, BillOfLoading> find = new Finder<>(BillOfLoading.class);


    @OneToOne(cascade = CascadeType.ALL)
    public Container container;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    public Company company;

    @OneToOne
    public Order order;

}
