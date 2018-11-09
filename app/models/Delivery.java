package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Delivery extends BaseModel {
    public static final Finder<Long, Delivery> find = new Finder<>(Delivery.class);

    @NotNull
    public String container="";

    @NotNull
    public String licensePlate="";

    @NotNull
    public String driverFullName="";

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date arrivalToPlant=new Date();

    @NotNull
    @Formats.DateTime(pattern = "hh:mm")
    public Date containerDischargeStart = new Date();

    @NotNull
    @Formats.DateTime(pattern = "hh:mm")
    public Date containerDischargeEnd = new Date();

    @NotNull
    @Formats.DateTime(pattern = "hh:mm")
    public Date blockDischargeStart = new Date();

    @NotNull
    @Formats.DateTime(pattern = "hh:mm")
    public Date blockDischargeEnd = new Date();

    @NotNull
    public double damageFine=0;

    @NotNull
    @Formats.DateTime(pattern = "hh:mm")
    public Date returnDate = new Date();

    @NotNull
    public double lateReturnFine = 0;

    @NotNull
    @ManyToOne
    public PurchaseOrder purchaseOrder;

}
