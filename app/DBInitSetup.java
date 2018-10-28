import models.Company;

public class DBInitSetup {
    public DBInitSetup() {
        if (Company.find.all().isEmpty()){
            Company company = new Company();
            company.name="test";
            company.address="Calle alsina 123";
            company.save();
        }

    }

}
