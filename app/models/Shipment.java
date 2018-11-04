package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Shipment extends BaseModel {
    public static final Finder<Long, Shipment> find = new Finder<>(Shipment.class);

    @OneToOne(cascade = CascadeType.ALL)
    public Container container;

    @OneToOne(cascade = CascadeType.ALL)
    public Truck truck;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date enterTime = new Date();

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date leaveTime = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    public Order Order;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Fine> fines = new ArrayList<>();
}
