package controllers;

import models.Fine;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class FineController extends Controller {
    public Result getAllFine() {
        return ok(Json.toJson(Fine.find.all()));
    }

    public Result getFine(long id) {
        try {
            return ok(Json.toJson(Fine.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putFine() {
        try {
            Fine Fine = Json.fromJson(request().body().asJson(), Fine.class);
            if (Fine.notSaved(Fine.id)) {
                Fine.save();
                return created().withHeaders("Location", request().uri() + "/" + Fine.id);
            } else if (Fine.isIdValid(Fine.id)) {
                if (Fine.find.byId(Fine.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    Fine.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postFine() {
        try {
            Fine Fine = Json.fromJson(request().body().asJson(), Fine.class);
            Fine.update();
            return ok(JsonMessage.make("Fine updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteFine(long id) {
        try {
            Fine.find.deleteById(id);
            return ok(JsonMessage.make("Fine deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
