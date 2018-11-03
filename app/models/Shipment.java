package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Shipment extends BaseModel {
    public static final Finder<Long, Shipment> find = new Finder<>(Shipment.class);


    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public Container container;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public Truck truck;

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date enterTime = new Date();

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date leaveTime = new Date();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    public Order Order;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    public List<Fine> fines = new ArrayList<>();
}
