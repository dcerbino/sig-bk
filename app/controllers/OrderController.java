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

    public Result getOrder(Long id) {
        try {
            return ok().sendJson(Json.toJson(Order.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putOrder() {
        try {
            Order order = Json.fromJson(request().body().asJson(), Order.class);
            if (order.notSaved(order.id)) {
                order.save();
                return created()
                        .sendJson(Json.toJson(order))
                        .withHeaders("Location", request().uri() + "/" + order.id);
            } else if (order.isIdValid(order.id)) {
                if (order.find.byId(order.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    order.save();
                    return created()
                            .sendJson(Json.toJson(order))
                            .withHeaders("Location", request().uri() + "/" + order.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postOrder() {
        try {
            Order order = Json.fromJson(request().body().asJson(), Order.class);
            order.update();
            return ok().sendJson(JsonMessage.make("Order updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteOrder(Long id) {
        try {
            Order.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Order deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
