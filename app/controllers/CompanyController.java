package controllers;

import models.Company;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class CompanyController extends Controller {
    public Result getAllCompany() {
        return ok().sendJson(Json.toJson(Company.find.all()));
    }

    public Result getCompany(long id) {
        try {
            return ok().sendJson(Json.toJson(Company.find.byId(id)));
        } catch (Exception e) {
//            e.printStackTrace();
            return noContent();
        }
    }

    public Result putCompany() {
        try {
            Company company = Json.fromJson(request().body().asJson(), Company.class);
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
            Company company = Json.fromJson(request().body().asJson(), Company.class);
            company.update();
            return ok().sendJson(JsonMessage.make("Company updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteCompany(long id) {
        try {
            Company.find.deleteById(id);
            return ok().sendJson(JsonMessage.make("Company deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
