package models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Fine extends BaseModel {

    @NotNull
    public String reason;

    @NotNull
    public Double ammount;

    @NotNull
    public String currency;
}
