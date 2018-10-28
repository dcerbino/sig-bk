package controllers;

import models.Shipment;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ShipmentController extends Controller {
    public Result getAllShipment() {
        return ok(Json.toJson(Shipment.find.all()));
    }

    public Result getShipment(long id) {
        try {
            return ok(Json.toJson(Shipment.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putShipment() {
        try {
            Shipment Shipment = Json.fromJson(request().body().asJson(), Shipment.class);
            if (Shipment.notSaved(Shipment.id)) {
                Shipment.save();
                return created().withHeaders("Location", request().uri() + Shipment.id);
            } else if (Shipment.isIdValid(Shipment.id)) {
                if (Shipment.find.byId(Shipment.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    Shipment.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postShipment() {
        try {
            Shipment Shipment = Json.fromJson(request().body().asJson(), Shipment.class);
            Shipment.update();
            return ok(JsonMessage.make("Shipment updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteShipment(long id) {
        try {
            Shipment.find.deleteById(id);
            return ok(JsonMessage.make("Shipment deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}

