package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Truck extends BaseModel {


    @NotNull
    @OneToOne
    public Container container;

    @NotNull
    public String driver = "";


    @NotNull
    public String licensePlate = "";

}
