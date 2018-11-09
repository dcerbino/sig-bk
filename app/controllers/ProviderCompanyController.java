package controllers;

import models.ProviderCompany;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class ProviderCompanyController extends Controller {
    public Result getAllCompany() {
        return ok().sendJson(Json.toJson(ProviderCompany.find.all()));
    }

    public Result getCompany(long id) {
        try {
            return ok().sendJson(Json.toJson(ProviderCompany.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putCompany() {
        try {
            ProviderCompany company = Json.fromJson(request().body().asJson(), ProviderCompany.class);
            if (company.notSaved(company.id)) {
                company.save();
                return created()
                        .sendJson(Json.toJson(company))
                        .withHeaders("Location", request().uri() + "/" +company.id);
            } else if (company.isIdValid(company.id)) {
                if (company.find.byId(company.id) != null) {
                    return badRequest().sendJson(JsonMessage.make("Object already exist"));
                } else {
                    company.save();
                    return created()
                            .sendJson(Json.toJson(company))
                            .withHeaders("Location", request().uri() + "/" +company.id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest().sendJson(JsonMessage.make("error wile saving object"));
    }

    public Result postCompany() {
        try {
            ProviderCompany company = Json.fromJson(request().body().asJson(), ProviderCompany.class);
            company.update();
            return ok().sendJson(JsonMessage.make("ProviderCompany updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteCompany(long id) {
        try {
            ProviderCompany.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("ProviderCompany deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
