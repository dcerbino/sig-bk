package models;

import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Container extends BaseModel {

    @ManyToOne
    public Product product;

    @NotNull
    public int footSize;

}
