package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Container extends BaseModel {
    public static final Finder<Long, Container> find = new Finder<>(Container.class);


    @ManyToOne
    public Product product;

    @NotNull
    public int footSize = 0;

}
