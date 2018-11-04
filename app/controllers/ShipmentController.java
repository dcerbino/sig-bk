package controllers;

import models.Shipment;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ShipmentController extends Controller {
    public Result getAllShipment() {
        return ok().sendJson(Json.toJson(Shipment.find.all()));
    }

    public Result getShipment(long id) {
        try {
            return ok().sendJson(Json.toJson(Shipment.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putShipment() {
        try {
            Shipment shipment = Json.fromJson(request().body().asJson(), Shipment.class);
            if (shipment.notSaved(shipment.id)) {
                shipment.save();
                return created()
                        .sendJson(Json.toJson(shipment))
                        .withHeaders("Location", request().uri() + "/" + shipment.id);
            } else if (shipment.isIdValid(shipment.id)) {
                if (shipment.find.byId(shipment.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    shipment.save();
                    return created()
                            .sendJson(Json.toJson(shipment))
                            .withHeaders("Location", request().uri() + "/" + shipment.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postShipment() {
        try {
            Shipment Shipment = Json.fromJson(request().body().asJson(), Shipment.class);
            Shipment.update();
            return ok().sendJson(JsonMessage.make("Shipment updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteShipment(long id) {
        try {
            Shipment.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Shipment deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}

