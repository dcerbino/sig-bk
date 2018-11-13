import models.Delivery;
import models.Product;
import models.ProviderCompany;
import models.PurchaseOrder;

public class DBInitSetup {
    public DBInitSetup() {
        if (Delivery.find.all().isEmpty()){
            Product product = new Product();
            product.name="product1";
            product.save();

            ProviderCompany providerCompany = new ProviderCompany();
            providerCompany.name="company1";
            providerCompany.save();

            Delivery delivery = new Delivery();
            delivery.container="123454";
            delivery.driverFullName="aaabbb";
            delivery.save();

            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.product = product;
            purchaseOrder.delivery = delivery;
            purchaseOrder.provider= providerCompany;

            purchaseOrder.save();
        }
    }

}
