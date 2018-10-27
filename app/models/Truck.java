package models;

import io.ebean.Finder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Truck extends BaseModel {
    public static final Finder<Long, Truck> find = new Finder<>(Truck.class);


    @NotNull
    @OneToOne
    public Container container;

    @NotNull
    public String driver = "";


    @NotNull
    public String licensePlate = "";

}
