package controllers;

import models.Fine;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class FineController extends Controller {
    public Result getAllFine() {
        return ok().sendJson(Json.toJson(Fine.find.all()));
    }

    public Result getFine(long id) {
        try {
            return ok().sendJson(Json.toJson(Fine.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putFine() {
        try {
            Fine fine = Json.fromJson(request().body().asJson(), Fine.class);
            if (fine.notSaved(fine.id)) {
                fine.save();
                return created()
                        .sendJson(Json.toJson(fine))
                        .withHeaders("Location", request().uri() + "/" + fine.id);
            } else if (fine.isIdValid(fine.id)) {
                if (fine.find.byId(fine.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    fine.save();
                    return created()
                            .sendJson(Json.toJson(fine))
                            .withHeaders("Location", request().uri() + "/" + fine.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postFine() {
        try {
            Fine fine = Json.fromJson(request().body().asJson(), Fine.class);
            fine.update();
            return ok().sendJson(JsonMessage.make("Fine updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteFine(long id) {
        try {
            Fine.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Fine deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
