package controllers;

import models.Company;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonMessage;

public class CompanyController extends Controller {
    public Result getAllCompany() {
        return ok(Json.toJson(Company.find.all()));
    }

    public Result getCompany(long id) {
        try {
            return ok(Json.toJson(Company.find.byId(id)));
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
                return created().withHeaders("Location", request().uri() + company.id);
            } else if (company.isIdValid(company.id)) {
                if (company.find.byId(company.id) != null) {
                    return badRequest(JsonMessage.make("Object already exist"));
                } else {
                    company.save();
                    return created();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badRequest(JsonMessage.make("error wile saving object"));
    }

    public Result postCompany() {
        try {
            Company Company = Json.fromJson(request().body().asJson(), Company.class);
            Company.update();
            return ok(JsonMessage.make("Company updated"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

    public Result deleteCompany(long id) {
        try {
            Company.find.deleteById(id);
            return ok(JsonMessage.make("Company deleted"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noContent();
    }

}
