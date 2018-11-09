package controllers;

import models.PurchaseOrder;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class PurchaseOrderController extends Controller {
    public Result getAllOrder() {
        return ok(Json.toJson(PurchaseOrder.find.all()));
    }

    public Result getOrder(Long id) {
        try {
            return ok().sendJson(Json.toJson(PurchaseOrder.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putOrder() {
        try {
            PurchaseOrder order = Json.fromJson(request().body().asJson(), PurchaseOrder.class);
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
            PurchaseOrder order = Json.fromJson(request().body().asJson(), PurchaseOrder.class);
            order.update();
            return ok().sendJson(JsonMessage.make("PurchaseOrder updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteOrder(Long id) {
        try {
            PurchaseOrder.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("PurchaseOrder deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
