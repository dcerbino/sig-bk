package controllers;

import models.ProductType;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ProductTypeController extends Controller {
    public Result getAllProductType() {
        return ok(Json.toJson(ProductType.find.all()));
    }

    public Result getProductType(long id) {
        try {
            return ok(Json.toJson(ProductType.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putProductType() {
        try {
            ProductType ProductType = Json.fromJson(request().body().asJson(), ProductType.class);
            if (ProductType.notSaved(ProductType.id)) {
                ProductType.save();
                return created().withHeaders("Location", request().uri() + ProductType.id);
            } else if (ProductType.isIdValid(ProductType.id)) {
                if (ProductType.find.byId(ProductType.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    ProductType.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postProductType() {
        try {
            ProductType ProductType = Json.fromJson(request().body().asJson(), ProductType.class);
            ProductType.update();
            return ok(JsonMessage.make("ProductType updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteProductType(long id) {
        try {
            ProductType.find.deleteById(id);
            return ok(JsonMessage.make("ProductType deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
