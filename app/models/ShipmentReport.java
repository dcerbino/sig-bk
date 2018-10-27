package models;

import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ShipmentReport extends BaseModel {

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date introduced = new Date();

    @NotNull
    public String terminal = "";

    @NotNull
    public String port = "";

    @OneToOne
    public Company navyCompany;

    @OneToOne
    public Company provider;

    @NotNull
    public String loadingPoint = "";

    @NotNull
    public String boat = "";


    @NotNull
    public String deliveryPlace;


}
