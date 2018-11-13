package models;

import io.ebean.Finder;
import io.ebean.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Delivery extends BaseModel {
    public static final Finder<Long, Delivery> find = new Finder<>(Delivery.class);

    public String container;

    public String licensePlate;

    public String driverFullName;

    @Formats.DateTime(pattern = "yyyy-MM-ddTHH:mm")
    public Date arrivalToPlant;

    @Formats.DateTime(pattern = "yyyy-MM-ddTHH:mm")
    public Date containerDischargeStart;

    @Formats.DateTime(pattern = "yyyy-MM-ddTHH:mm")
    public Date containerDischargeEnd;

    @Formats.DateTime(pattern = "yyyy-MM-ddTHH:mm")
    public Date blockDischargeStart;

    @Formats.DateTime(pattern = "yyyy-MM-ddTHH:mm")
    public Date blockDischargeEnd;

    public Double damageFine;

    @Formats.DateTime(pattern = "yyyy-MM-ddTHH:mm")
    public Date returnDate;

    public Double lateReturnFine;

    @OneToOne
    @JsonIgnore
    public PurchaseOrder purchaseOrder;

}
