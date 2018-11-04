package controllers;

import models.ShipmentReport;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ShipmentReportController extends Controller {
    public Result getAllShipmentReport() {
        return ok(Json.toJson(ShipmentReport.find.all()));
    }

    public Result getShipmentReport(long id) {
        try {
            return ok(Json.toJson(ShipmentReport.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putShipmentReport() {
        try {
            ShipmentReport shipmentReport = Json.fromJson(request().body().asJson(), ShipmentReport.class);
            if (shipmentReport.notSaved(shipmentReport.id)) {
                shipmentReport.save();
                return created()
                        .sendJson(Json.toJson(shipmentReport))
                        .withHeaders("Location", request().uri() + "/" + shipmentReport.id);
            } else if (shipmentReport.isIdValid(shipmentReport.id)) {
                if (shipmentReport.find.byId(shipmentReport.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    shipmentReport.save();
                    return created()
                            .sendJson(Json.toJson(shipmentReport))
                            .withHeaders("Location", request().uri() + "/" + shipmentReport.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postShipmentReport() {
        try {
            ShipmentReport ShipmentReport = Json.fromJson(request().body().asJson(), ShipmentReport.class);
            ShipmentReport.update();
            return ok().sendJson(JsonMessage.make("ShipmentReport updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteShipmentReport(long id) {
        try {
            ShipmentReport.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("ShipmentReport deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
