package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Container extends BaseModelWithStringId {
    public static final Finder<String, Container> find = new Finder<>(Container.class);


    @ManyToOne(cascade = CascadeType.ALL)
    public Product product;

    @NotNull
    public int footSize = 0;

}
