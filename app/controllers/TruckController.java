package controllers;

import models.Truck;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class TruckController extends Controller {
    public Result getAllTruck() {
        return ok(Json.toJson(Truck.find.all()));
    }

    public Result getTruck(long id) {
        try {
            return ok(Json.toJson(Truck.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putTruck() {
        try {
            Truck Truck = Json.fromJson(request().body().asJson(), Truck.class);
            if (Truck.notSaved(Truck.id)) {
                Truck.save();
                return created().withHeaders("Location", request().uri() + "/" + Truck.id);
            } else if (Truck.isIdValid(Truck.id)) {
                if (Truck.find.byId(Truck.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    Truck.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postTruck() {
        try {
            Truck Truck = Json.fromJson(request().body().asJson(), Truck.class);
            Truck.update();
            return ok(JsonMessage.make("Truck updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteTruck(long id) {
        try {
            Truck.find.deleteById(id);
            return ok(JsonMessage.make("Truck deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
