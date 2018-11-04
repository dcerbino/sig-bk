package controllers;

import io.ebean.Ebean;
import models.Product;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ProductController extends Controller {
    public Result getAllProduct() {
        return ok(Json.toJson(Product.find.all()));
    }

    public Result getProduct(long id) {
        try {
            return ok(Json.toJson(Product.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putProduct() {
        try {
            Product product = Json.fromJson(request().body().asJson(), Product.class);
            if (product.notSaved(product.id)) {
                product.save();
                return created()
                        .sendJson(Json.toJson(Product.find.byId(product.id)))
                        .withHeaders("Location", request().uri() + "/" + product.id);
            } else if (product.isIdValid(product.id)) {
                if (product.find.byId(product.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    product.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postProduct() {
        try {
            Product Product = Json.fromJson(request().body().asJson(), Product.class);
            Product.update();
            return ok(JsonMessage.make("Product updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteProduct(long id) {
        try {
            Product.find.deleteById(id);
            return ok(JsonMessage.make("Product deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
