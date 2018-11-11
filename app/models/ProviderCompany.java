package models;

import io.ebean.Finder;
import io.ebean.annotation.JsonIgnore;
import io.ebean.annotation.NotNull;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProviderCompany extends BaseModel {
    public static final Finder<Long, ProviderCompany> find = new Finder<>(ProviderCompany.class);

    @NotNull
    public String name = "";

    @OneToMany
    @JsonIgnore
    List<PurchaseOrder> purchaseOrders = new ArrayList<>();

}
