package controllers;

import models.BillOfLoading;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class BillOfLoadingController extends Controller {
    public Result getAllBillOfLoading() {
        return ok().sendJson(Json.toJson(BillOfLoading.find.all()));
    }

    public Result getBillOfLoading(long id) {
        try {
            return ok().sendJson(Json.toJson(BillOfLoading.find.byId(id)));
        } catch (Exception e) {
            return noContent();
        }
    }

    public Result putBillOfLoading() {
        try {
            BillOfLoading billOfLoading = Json.fromJson(request().body().asJson(), BillOfLoading.class);
            if (billOfLoading.notSaved(billOfLoading.id)) {
                billOfLoading.save();
                return created()
                        .sendJson(Json.toJson(billOfLoading))
                        .withHeaders("Location", request().uri() + billOfLoading.id);
            } else if (billOfLoading.isIdValid(billOfLoading.id)) {
                if (billOfLoading.find.byId(billOfLoading.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    billOfLoading.save();
                    return created()
                            .sendJson(Json.toJson(billOfLoading))
                            .withHeaders("Location", request().uri() + billOfLoading.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postBillOfLoading() {
        try {
            BillOfLoading billOfLoading = Json.fromJson(request().body().asJson(), BillOfLoading.class);
            billOfLoading.update();
            return ok().sendJson(JsonMessage.make("BillOfLoading updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteBillOfLoading(long id) {
        try {
            BillOfLoading.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("BillOfLoading deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}

