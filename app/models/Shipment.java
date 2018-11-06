package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Shipment extends BaseModel {
    public static final Finder<Long, Shipment> find = new Finder<>(Shipment.class);

    @ManyToOne(cascade = CascadeType.ALL)
    public Container container;

    @OneToOne(cascade = CascadeType.ALL)
    public Truck truck;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date enterTime = new Date();

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date leaveTime = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    public Order order;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Fine> fines = new ArrayList<>();
}
