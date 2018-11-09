package controllers;

import models.Delivery;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class DeliveryController extends Controller {
    public Result getAllDelivery() {
        return ok().sendJson(Json.toJson(Delivery.find.all()));
    }

    public Result getDelivery(long id) {
        try {
            return ok().sendJson(Json.toJson(Delivery.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putDelivery() {
        try {
            Delivery delivery = Json.fromJson(request().body().asJson(), Delivery.class);
            if (delivery.notSaved(delivery.id)) {
                delivery.save();
                return created()
                        .sendJson(Json.toJson(delivery))
                        .withHeaders("Location", request().uri() + "/" + delivery.id);
            } else if (delivery.isIdValid(delivery.id)) {
                if (delivery.find.byId(delivery.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    delivery.save();
                    return created()
                            .sendJson(Json.toJson(delivery))
                            .withHeaders("Location", request().uri() + "/" + delivery.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postDelivery() {
        try {
            Delivery delivery = Json.fromJson(request().body().asJson(), Delivery.class);
            delivery.update();
            return ok().sendJson(JsonMessage.make("Delivery updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteDelivery(long id) {
        try {
            Delivery.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Delivery deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}

