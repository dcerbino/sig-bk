package controllers;

import models.BillOfLoading;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class BillOfLoadingController extends Controller {
    public Result getAllBillOfLoading() {
        return ok(Json.toJson(BillOfLoading.find.all()));
    }

    public Result getBillOfLoading(long id) {
        try {
            return ok(Json.toJson(BillOfLoading.find.byId(id)));
        } catch (Exception e) {
            return noContent();
        }
    }

    public Result putBillOfLoading() {
        try {
            BillOfLoading BillOfLoading = Json.fromJson(request().body().asJson(), BillOfLoading.class);
            if (BillOfLoading.notSaved(BillOfLoading.id)) {
                BillOfLoading.save();
                return created().withHeaders("Location", request().uri() + BillOfLoading.id);
            } else if (BillOfLoading.isIdValid(BillOfLoading.id)) {
                if (BillOfLoading.find.byId(BillOfLoading.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    BillOfLoading.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postBillOfLoading() {
        try {
            BillOfLoading BillOfLoading = Json.fromJson(request().body().asJson(), BillOfLoading.class);
            BillOfLoading.update();
            return ok(JsonMessage.make("BillOfLoading updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteBillOfLoading(long id) {
        try {
            BillOfLoading.find.deleteById(id);
            return ok(JsonMessage.make("BillOfLoading deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}

