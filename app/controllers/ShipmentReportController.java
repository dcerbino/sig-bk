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
            ShipmentReport ShipmentReport = Json.fromJson(request().body().asJson(), ShipmentReport.class);
            if (ShipmentReport.notSaved(ShipmentReport.id)) {
                ShipmentReport.save();
                return created().withHeaders("Location", request().uri() + ShipmentReport.id);
            } else if (ShipmentReport.isIdValid(ShipmentReport.id)) {
                if (ShipmentReport.find.byId(ShipmentReport.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    ShipmentReport.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postShipmentReport() {
        try {
            ShipmentReport ShipmentReport = Json.fromJson(request().body().asJson(), ShipmentReport.class);
            ShipmentReport.update();
            return ok(JsonMessage.make("ShipmentReport updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteShipmentReport(long id) {
        try {
            ShipmentReport.find.deleteById(id);
            return ok(JsonMessage.make("ShipmentReport deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
