package models;

import play.data.format.Formats;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "torder")
public class Order extends BaseModel {

    @OneToMany(cascade = CascadeType.ALL)
    public List<Product> products = new ArrayList<>();

    @NotNull
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date date;

    @NotNull
    @OneToOne
    public Company company;

    @NotNull
    @OneToOne
    public BillOfLoading billOfLoading;
}
