package models;

import io.ebean.Finder;
import play.data.format.Formats;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ShipmentReport extends BaseModel {
    public static final Finder<Long, ShipmentReport> find = new Finder<>(ShipmentReport.class);

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date introduced = new Date();

    public String terminal = "";

    public String port = "";

    @OneToOne(cascade = CascadeType.ALL)
    public Company navyCompany;

    @OneToOne(cascade = CascadeType.ALL)
    public Company provider;

    public String loadingPoint = "";

    public String dockingPoint = "";

    public String boat = "";

    public String deliveryPlace = "";

}
