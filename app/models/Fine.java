package models;

import io.ebean.Finder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Fine extends BaseModel {
    public static final Finder<Long, Fine> find = new Finder<>(Fine.class);

    @NotNull
    public String reason = "";

    @NotNull
    public Double amount = 0D;

    @NotNull
    public String currency = "";
}
