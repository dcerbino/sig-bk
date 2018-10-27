package models;

import io.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ShipmentReport extends Model {
    @Id
    private int id=0;

    @NotNull
    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date introduced;

    @NotNull
    private String terminal;

    @NotNull
    private String port;

    @OneToOne
    private Company navyCompany;

    @OneToOne
    private Company provider;

    @NotNull
    private String loadingPoint;

    @NotNull
    private String boat;

    @NotNull
    private String deliveryPlace;



}
