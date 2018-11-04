package controllers;

import models.Truck;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class TruckController extends Controller {
    public Result getAllTruck() {
        return ok().sendJson(Json.toJson(Truck.find.all()));
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
            Truck truck = Json.fromJson(request().body().asJson(), Truck.class);
            if (truck.notSaved(truck.id)) {
                truck.save();
                return created()
                        .sendJson(Json.toJson(truck))
                        .withHeaders("Location", request().uri() + "/" + truck.id);
            } else if (truck.isIdValid(truck.id)) {
                if (truck.find.byId(truck.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    truck.save();
                    return created()
                            .sendJson(Json.toJson(truck))
                            .withHeaders("Location", request().uri() + "/" + truck.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postTruck() {
        try {
            Truck Truck = Json.fromJson(request().body().asJson(), Truck.class);
            Truck.update();
            return ok().sendJson(JsonMessage.make("Truck updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteTruck(long id) {
        try {
            Truck.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Truck deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
