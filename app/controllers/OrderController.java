package controllers;

import models.Order;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class OrderController extends Controller {
    public Result getAllOrder() {
        return ok(Json.toJson(Order.find.all()));
    }

    public Result getOrder(long id) {
        try {
            return ok(Json.toJson(Order.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putOrder() {
        try {
            Order Order = Json.fromJson(request().body().asJson(), Order.class);
            if (Order.notSaved(Order.id)) {
                Order.save();
                return created().withHeaders("Location", request().uri() + Order.id);
            } else if (Order.isIdValid(Order.id)) {
                if (Order.find.byId(Order.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    Order.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postOrder() {
        try {
            Order Order = Json.fromJson(request().body().asJson(), Order.class);
            Order.update();
            return ok(JsonMessage.make("Order updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteOrder(long id) {
        try {
            Order.find.deleteById(id);
            return ok(JsonMessage.make("Order deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
