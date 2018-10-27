package models;

import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class BillOfLoading extends BaseModel {

    @OneToOne
    public Container container;

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date;

    @NotNull
    @OneToOne
    public Company company;

}
