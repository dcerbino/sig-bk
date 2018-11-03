import models.Company;
import models.Product;
import models.ProductType;

public class DBInitSetup {
    public DBInitSetup() {
        if (Company.find.all().isEmpty()){
            Company company = new Company();
            company.name="test";
            company.address="Calle alsina 123";
            company.save();
        }
        if (ProductType.find.all().isEmpty()){
            ProductType pt = new ProductType();
            pt.material="Madera";
            pt.name="Ebano";
            pt.color="maron";
            pt.save();
        }

        if (Product.find.all().isEmpty()){
            Product product =new Product();
            product.name="P1";
            product.productType=ProductType.find.all().get(0);
            product.save();
        }
    }

}
