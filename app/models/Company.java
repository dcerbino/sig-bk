package models;

import io.ebean.Model;
import io.ebean.annotation.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Company extends Model{
    @Id
    private int id = 0;

    @NotNull
    private String name;

    @NotNull
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }
}