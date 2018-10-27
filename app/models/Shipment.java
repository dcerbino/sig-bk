package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Shipment extends BaseModel {
    public static final Finder<Long, Shipment> find = new Finder<>(Shipment.class);


    @NotNull
    @OneToOne
    public Container container;

    @NotNull
    @OneToOne
    public Truck trunk;

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date enterTime = new Date();

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date leaveTime = new Date();
    ;

    @NotNull
    @OneToOne
    public Order Order;

    @NotNull
    @OneToOne
    public Fine fine;
}
