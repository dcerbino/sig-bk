package controllers;

import models.Container;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ContainerController extends Controller {
    public Result getAllContainer() {
        return ok().sendJson(Json.toJson(Container.find.all()));
    }

    public Result getContainer(String id) {
        try {
            return ok().sendJson(Json.toJson(Container.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putContainer() {
        try {
            Container container = Json.fromJson(request().body().asJson(), Container.class);
            if (container.notSaved(container.id)) {
                container.save();
                return created()
                        .sendJson(Json.toJson(container))
                        .withHeaders("Location", request().uri() + "/" + container.id);
            } else if (container.isIdValid(container.id)) {
                if (container.find.byId(container.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    container.save();
                    return created()
                            .sendJson(Json.toJson(container))
                            .withHeaders("Location", request().uri() + "/" + container.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postContainer() {
        try {
            Container container = Json.fromJson(request().body().asJson(), Container.class);
            container.update();
            return ok().sendJson(JsonMessage.make("Container updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteContainer(String id) {
        try {
            Container.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Container deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}

