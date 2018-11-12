package models;

import io.ebean.Finder;
import io.ebean.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import play.data.format.Formats;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Delivery extends BaseModel {
    public static final Finder<Long, Delivery> find = new Finder<>(Delivery.class);

    @NotNull
    public String container="";

    @NotNull
    public String licensePlate="";

    @NotNull
    public String driverFullName="";

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date arrivalToPlant;

    @Formats.DateTime(pattern = "hh:mm")
    public Date containerDischargeStart;

    @Formats.DateTime(pattern = "hh:mm")
    public Date containerDischargeEnd;

    @Formats.DateTime(pattern = "hh:mm")
    public Date blockDischargeStart;

    @Formats.DateTime(pattern = "hh:mm")
    public Date blockDischargeEnd;

    public double damageFine;

    @Formats.DateTime(pattern = "hh:mm")
    public Date returnDate;

    public double lateReturnFine = 0;

    @OneToOne
    @JsonIgnore
    public PurchaseOrder purchaseOrder;

}
