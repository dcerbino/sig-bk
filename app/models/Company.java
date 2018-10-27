package models;

import io.ebean.annotation.NotNull;

import javax.persistence.Entity;


@Entity
public class Company extends BaseModel {

    @NotNull
    public String name;

    @NotNull
    public String address;


}