package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;


@Entity
public class Company extends BaseModel {
    public static final Finder<Long, Company> find = new Finder<>(Company.class);

    @NotNull
    public String name = "";

    @NotNull
    public String address = "";


}